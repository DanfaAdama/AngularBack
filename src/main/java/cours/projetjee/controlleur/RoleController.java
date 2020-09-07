package cours.projetjee.controlleur;

import cours.projetjee.dao.RoleRepository;
import cours.projetjee.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RoleController {
    @Autowired
    private RoleRepository roleRepository ;

    @GetMapping("/roles/liste")
    public ResponseEntity<?> listeRoles() {
        return ResponseEntity.ok(roleRepository
                .findAll());
    }
}
