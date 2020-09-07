package cours.projetjee.dao;

import cours.projetjee.model.Commande;
import cours.projetjee.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande,Long> {
    @Query("select max(c.id) + 1 from Commande c ")
    Optional<Commande> getLastInsertedIdIncremented();

    public Commande getCommandeById(Long id);


    @Query("select max(c) from Commande c where c.user.id =:clientId ")
    Optional<Commande> getCommandeByUserId(Long clientId);

    @Query("SELECT c from Commande c where c.user.id =:clientId ORDER BY c.numero ASC")
    List<Commande> getCommandesByClient(Long clientId);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Commande c set c.avance = c.avance + :Newavance  where c.id =:id")
    int UpdateCommandeAvance(@Param("id") Long id, @Param("Newavance") Float Newavance);

}
