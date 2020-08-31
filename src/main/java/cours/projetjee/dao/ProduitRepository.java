package cours.projetjee.dao;

import cours.projetjee.model.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    public List<Produit> findByCategorieLibelle(String categorie);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Produit p set p.quantite = :qte  where p.id =:id")
    void updateQtePoduit(@Param("id") Long id, @Param("qte") int qte);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Produit p set p = p.quantite - :qte  where p.id =:id")
    void reduceQtePoduit(@Param("id") Long id, @Param("qte") int qte);


}
