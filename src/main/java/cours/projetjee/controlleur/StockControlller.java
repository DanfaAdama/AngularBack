package cours.projetjee.controlleur;

import cours.projetjee.dao.ProduitRepository;
import cours.projetjee.dao.StockRepository;
import cours.projetjee.model.ErrorResponse;
import cours.projetjee.model.Produit;
import cours.projetjee.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/ressources/stocks")
public class StockControlller {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProduitRepository produitRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public ResponseEntity<?> addStock(@RequestBody Stock stock) {
        Optional<Produit> p = produitRepository.findById((long) stock.getId_produit());
        stock.setProduit(p.get());
        produitRepository.updateQtePoduit(p.get().getId(),(int)stock.getQuantiteStock());
        stockRepository.save(stock);
        return ResponseEntity.ok(stock);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStock(@PathVariable("id") Long id) {
        try {
            stockRepository.deleteById(id);
            return ResponseEntity.ok("Produit supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }


    @PutMapping("/update")
    public @ResponseBody Stock update(@RequestBody Stock stock) {
        return stockRepository.save(stock);

    }

    @GetMapping("/liste")
    public ResponseEntity<?> listeStock() {
        return ResponseEntity.ok(stockRepository
                .findAll());
    }
}
