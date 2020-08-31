package cours.projetjee.controlleur;
import cours.projetjee.dao.CommandeRepository;
import cours.projetjee.dao.ProduitRepository;
import cours.projetjee.model.Commande;
import cours.projetjee.model.DetailCommande;
import org.json.JSONObject;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xhtmlrenderer.resource.XMLResource;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PdfController {
    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    ProduitRepository produitRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping(value = "/sendFactureByMail/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String SendFacturePdfByMail(@PathVariable Long id) throws IOException, DocumentException {
        int num = 1;
        long good=num;
        Context context = new Context();
        List<DetailCommande> details_commandeList = new ArrayList<>();
        Commande commande = commandeRepository.getCommandeById(id);
        for (DetailCommande details:commande.getDetails_commandes()) {
            details_commandeList.add(details);
        }
        LocalDate date = LocalDate.now();

        Double montantNet = commande.getMontant()  - commande.getAvance();
        context.setVariable("TodayDate",date);
        context.setVariable("commande",commande);
        context.setVariable("montant",formatPrice(commande.getMontant()));
        context.setVariable("net",formatPrice(montantNet));
        context.setVariable("avance",formatPrice(commande.getAvance()));
        context.setVariable("details_commandeList",details_commandeList);

        String htmlContentToRender = templateEngine.process("facture", context);
        String xHtml = xhtmlConvert(htmlContentToRender);

        ByteArrayResource htmlBytes = new ByteArrayResource(xHtml.getBytes(StandardCharsets.UTF_8));
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        org.w3c.dom.Document document = XMLResource.load(new ByteArrayInputStream(xHtml.getBytes())).getDocument();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument( document, null );
        renderer.layout();
        renderer.createPDF(byteArrayOutputStream);
        renderer.finishPDF();
        byteArrayOutputStream.close();
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(commande.getUser().getEmail());
            helper.setSubject("Facture Commande sur DANFA MULTUMEDIA");
            helper.setText("Vous trouverez en piece jointe votre facture en format pdf");
            helper.addAttachment("Facture" + ".pdf", new ByteArrayResource(byteArrayOutputStream.toByteArray()));

            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return JSONObject.quote("Facture envoyée avec succès");

    }
    public static String formatPrice(double value) {
        DecimalFormat formatter;
        if (value<=99999)
            formatter = new DecimalFormat("###,###,##0.00");
        else
            formatter = new DecimalFormat("#,##,##,###.00");

        return formatter.format(value);
    }
    private String xhtmlConvert(String html) throws UnsupportedEncodingException {
        Tidy tidy = new Tidy();
        tidy.setInputEncoding("UTF-8");
        tidy.setOutputEncoding("UTF-8");
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString("UTF-8");
    }

    @GetMapping(value ="/sendEmail")
    public String SendEmail(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("adanfaisidk@groupeisi.com");
        message.setSubject("DANFA MULTUMEDIA");
        message.setText("Hello World !!!");
        javaMailSender.send(message);

        return "Email send successfully";
    }
}
