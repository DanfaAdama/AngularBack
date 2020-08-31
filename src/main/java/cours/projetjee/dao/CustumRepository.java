package cours.projetjee.dao;

import cours.projetjee.model.Custumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustumRepository extends JpaRepository<Custumer,Long> {
}
