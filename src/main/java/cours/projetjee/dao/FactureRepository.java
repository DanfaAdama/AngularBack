package cours.projetjee.dao;

import cours.projetjee.model.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FactureRepository extends JpaRepository<Facture, Long> {

    @Query("select max(f.numero)  from Facture f where f.commande.id =:id")
    String getLastInsertedNumeroIncremented(@Param("id") Long id);

    @Query("select f from Facture f where f.id=:id")
    Facture getFactureById(Long id);
}
