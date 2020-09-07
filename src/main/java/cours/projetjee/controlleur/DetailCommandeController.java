package cours.projetjee.controlleur;

import cours.projetjee.dao.CommandeRepository;
import cours.projetjee.dao.DetailCommandeRepository;
import cours.projetjee.dao.ProduitRepository;
import cours.projetjee.dao.StockRepository;
import cours.projetjee.model.DetailCommande;
import cours.projetjee.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class DetailCommandeController {
    @Autowired
    private DetailCommandeRepository detailCommandeRepository;
    @Autowired
    private ProduitRepository produitRepository;


    @Autowired
    private StockRepository stockRepository;


    @GetMapping(value ="/liste")
    public List<DetailCommande> findAll(){
        return  detailCommandeRepository.findAll();
    }

    @GetMapping(value = "/get/{id}")
    public Optional<DetailCommande> getOne(@PathVariable Long id){
        return  detailCommandeRepository.findById(id);
    }


    @PostMapping(value = "/add")
    public DetailCommande save(@RequestBody DetailCommande dc){
        int qte;
    stockRepository.reduceQteStockPoduit( dc.getProduit().getId(), dc.getQuantite());
        Produit p =dc.getProduit();
        qte= p.getQuantite() - dc.getQuantite();
        p.setQuantite(qte);
        dc.setProduit(p);
        produitRepository.save(p);
//    produitRepository.reduceQtePoduit(dc.getProduit().getId(),dc.getQuantite());
        return  detailCommandeRepository.save(dc);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id){
        Optional<DetailCommande> details_commande = detailCommandeRepository.findById(id);
        stockRepository.increaseQteStockPoduit(details_commande.get().getProduit().getId(),(int) details_commande.get().getQuantite());
        detailCommandeRepository.deleteById(id);
    }


    @PutMapping(value = "/detail_commande/{id}")
    public DetailCommande update(@RequestBody DetailCommande dc, @PathVariable Long id){
        dc.setId(id);
        return  detailCommandeRepository.save(dc);
    }
}
