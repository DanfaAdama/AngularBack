package cours.projetjee.dao;

import cours.projetjee.model.Role;
import cours.projetjee.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    public Role findByName(RoleName roleName);

}
