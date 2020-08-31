package cours.projetjee.dao;

import cours.projetjee.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Stock st set st.quantiteStock = st.quantiteStock - :qte  where st.produit.id =:id")
    void reduceQteStockPoduit(@Param("id") Long id, @Param("qte") int qte);

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update Stock st set st.quantiteStock = st.quantiteStock + :qte  where st.produit.id =:id")
    void increaseQteStockPoduit(@Param("id") Long id, @Param("qte") int qte);
}
