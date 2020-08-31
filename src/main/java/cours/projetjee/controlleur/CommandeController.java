package cours.projetjee.controlleur;

import cours.projetjee.dao.CategoryRepository;
import cours.projetjee.dao.CommandeRepository;
import cours.projetjee.dao.ProduitRepository;
import cours.projetjee.dao.UserRepository;
import cours.projetjee.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.*;

@CrossOrigin
@Controller
@RequestMapping("/ressources/commandes")
public class CommandeController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @PostMapping("/add")
    public @ResponseBody Commande addCommande(@RequestBody Commande commande) {
        LocalDate date = LocalDate.now();
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        commande.setDatecommande(date);
        //Numero fatcure
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int month = cal.get(Calendar.MONTH)+1;
        if(commandeRepository.getLastInsertedIdIncremented().isPresent()){
            commande.setNumero(cal.get(Calendar.YEAR)+"-"+month+"-"+"00"+commandeRepository.getLastInsertedIdIncremented().get());
        }else {
            commande.setNumero(cal.get(Calendar.YEAR)+"-"+month+"-"+"001");
        }
        return commandeRepository.save(commande);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCommande(@PathVariable("id") Long id) {
        try {
            commandeRepository.deleteById(id);
            return ResponseEntity.ok("Commande supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }


    @PutMapping("/update")
    public @ResponseBody
    Commande update(@RequestBody Commande commande) {
        return commandeRepository.save(commande);
    }

    @GetMapping("/liste")
    public ResponseEntity<?> listeCommande() {
        return ResponseEntity.ok(commandeRepository
                .findAll());
    }

}
