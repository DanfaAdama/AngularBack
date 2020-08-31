package cours.projetjee.controlleur;

import cours.projetjee.dao.CategoryRepository;
import cours.projetjee.dao.PaiementRepository;
import cours.projetjee.model.Categorie;
import cours.projetjee.model.ErrorResponse;
import cours.projetjee.model.Paiement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ressources/paiement")
public class PaiementController {

    @Autowired
    private PaiementRepository   paiementRepository;


    @PostMapping("/add")
    public ResponseEntity<?> addPaiement(@RequestBody Paiement paiement) {
        paiementRepository.save(paiement);
        return ResponseEntity.ok(paiement);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePaiement(@PathVariable("id") Long id) {
        try {
            paiementRepository.deleteById(id);
            return ResponseEntity.ok("Paiement supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    @GetMapping("/liste")
    public ResponseEntity<?> listePaiements() {
        return ResponseEntity.ok(paiementRepository
                .findAll());
    }


    @PutMapping("/update")
    public @ResponseBody Paiement update(@RequestBody Paiement paiement) {
        return paiementRepository.save(paiement);
    }
}
