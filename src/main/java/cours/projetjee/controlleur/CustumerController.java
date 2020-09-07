package cours.projetjee.controlleur;

import cours.projetjee.dao.CustumRepository;
import cours.projetjee.model.Categorie;
import cours.projetjee.model.Custumer;
import cours.projetjee.model.ErrorResponse;
import cours.projetjee.model.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ressources/custumers")
public class CustumerController {
    @Autowired
    CustumRepository custumRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public ResponseEntity<?> addCustumer(@RequestBody Custumer custumer) {
        custumRepository.save(custumer);
        return ResponseEntity.ok(custumer);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustumer(@PathVariable("id") Long id) {
        try {
            custumRepository.deleteById(id);
            return ResponseEntity.ok("Client  supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    //@PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @GetMapping("/liste")
    public ResponseEntity<?> listeCustumers() {
        return ResponseEntity.ok(custumRepository
                .findAll());
    }


    @PutMapping("/update")
    public @ResponseBody
    Custumer update(@RequestBody Custumer custumer) {
        return custumRepository.save(custumer);
    }
}
