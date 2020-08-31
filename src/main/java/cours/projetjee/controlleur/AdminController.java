package cours.projetjee.controlleur;

import cours.projetjee.dao.AdminRepository;
import cours.projetjee.model.Admin;
import cours.projetjee.model.Custumer;
import cours.projetjee.model.ErrorResponse;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.Context;
import java.io.IOException;

@CrossOrigin
@Controller
    @RequestMapping("/ressources/admin")
public class AdminController {
    @Autowired
    AdminRepository adminRepository;

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/add")
    public ResponseEntity<?> addAdmin(@RequestBody Admin admin ) {
        adminRepository.save(admin);
        return ResponseEntity.ok(admin);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("id") int id) {
        try {
            adminRepository.deleteById(id);
            return ResponseEntity.ok("Admin  supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @GetMapping("/liste")
    public ResponseEntity<?> listeAdmins() {
        return ResponseEntity.ok(adminRepository
                .findAll());
    }


    @PutMapping("/update")
    public @ResponseBody
    Admin update(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }


}
