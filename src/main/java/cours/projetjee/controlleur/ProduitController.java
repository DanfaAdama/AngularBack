package cours.projetjee.controlleur;

import cours.projetjee.dao.CategoryRepository;
import cours.projetjee.dao.ProduitRepository;
import cours.projetjee.model.Categorie;
import cours.projetjee.model.ErrorResponse;
import cours.projetjee.model.Produit;
import cours.projetjee.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@Controller
@RequestMapping("/ressources/produits")
public class ProduitController {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public ResponseEntity<?> addProduit(@RequestBody Produit produit) {
        Optional<Categorie> c = categoryRepository.findById((long) produit.getId_categorie());
        produit.setCategorie(c.get());
        produitRepository.save(produit);
        return ResponseEntity.ok(produit);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduit(@PathVariable("id") Long id) {
        try {
            produitRepository.deleteById(id);
            return ResponseEntity.ok("Produit supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }


    @PutMapping("/update")
    public @ResponseBody
    Produit update(@RequestBody Produit produit) {
        return produitRepository.save(produit);
    }

    @GetMapping("/liste")
    public ResponseEntity<?> listeProduit() {
        return ResponseEntity.ok(produitRepository
                .findAll());
    }


    @GetMapping("/rechercher/{categorie}")
    public @ResponseBody
    List<Produit> searchByService(@PathVariable("categorie") String categorie){
        return produitRepository.findByCategorieLibelle(categorie);
    }
}
