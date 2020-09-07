package cours.projetjee.controlleur;

import cours.projetjee.dao.FactureRepository;
import cours.projetjee.dao.PaiementRepository;
import cours.projetjee.model.ErrorResponse;
import cours.projetjee.model.Facture;
import cours.projetjee.model.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ressources/factures")
public class FactureController {

    @Autowired
    private FactureRepository factureRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addFacture(@RequestBody Facture facture) {
        if(factureRepository.getLastInsertedNumeroIncremented(facture.getCommande().getId()) != null){
            int i=Integer.parseInt(factureRepository.getLastInsertedNumeroIncremented(facture.getCommande().getId())) + 1;
            facture.setNumero(String.valueOf(i));
        }else {
            facture.setNumero("1");
        }        factureRepository.save(facture);
        return ResponseEntity.ok(facture);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFacture(@PathVariable("id") Long id) {
        try {
            factureRepository.deleteById(id);
            return ResponseEntity.ok("Facture supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    @GetMapping("/liste")
    public ResponseEntity<?> listeFactures() {
        return ResponseEntity.ok(factureRepository
                .findAll());
    }
    @GetMapping(value = "/facture/{id}")
    public Optional<Facture> getOne(@PathVariable Long id){
        return  factureRepository.findById(id);
    }


    @PutMapping("/update")
    public @ResponseBody Facture update(@RequestBody Facture facture) {
        return factureRepository.save(facture);
    }
}
