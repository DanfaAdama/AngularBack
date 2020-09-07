package cours.projetjee.controlleur;

import cours.projetjee.dao.CategoryRepository;
import cours.projetjee.model.Categorie;
import cours.projetjee.model.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ressources/categories")
public class CategorieController {
    @Autowired
    private CategoryRepository categoryRepository;


    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public ResponseEntity<?> addCategories(@RequestBody Categorie categorie) {
        categoryRepository.save(categorie);
        return ResponseEntity.ok(categorie);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Long id) {
        try {
            categoryRepository.deleteById(id);
            return ResponseEntity.ok("Categorie supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    //@PreAuthorize("hasAuthority('ROLE_MEDECIN')")
    @GetMapping("/liste")
    public ResponseEntity<?> listeCategories() {
        return ResponseEntity.ok(categoryRepository
                .findAll());
    }


    @PutMapping("/update")
    public @ResponseBody Categorie update(@RequestBody Categorie categorie) {
        return categoryRepository.save(categorie);
    }
}
