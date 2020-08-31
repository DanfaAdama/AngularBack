package cours.projetjee.dao;

import cours.projetjee.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query("select max(c.id) + 1 from Commande c ")
    Optional<Commande> getLastInsertedIdIncremented();

    public Commande getCommandeById(Long id);

}
