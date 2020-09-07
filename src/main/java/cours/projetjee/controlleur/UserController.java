package cours.projetjee.controlleur;

import cours.projetjee.config.JwtTokenUtil;
import cours.projetjee.dao.RoleRepository;
import cours.projetjee.dao.UserRepository;
import cours.projetjee.model.*;
import cours.projetjee.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody JwtRequest authenticationRequest) {

        final UserDetails details = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtTokenUtil.generateToken(details);
            if (details != null)
                return ResponseEntity.ok(new ResponseJwt(jwt, details.getUsername(), details.getAuthorities()));
            return ResponseEntity.ok(new ErrorResponse("INVALID_CREDENTIALS"));
        } catch (DisabledException e) {
            return ResponseEntity.ok(new ErrorResponse("USER_DISABLED"));
        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(new ErrorResponse("INVALID_CREDENTIALS"));
        }
    }

    @PreAuthorize("hasAuthority('ROLE_ADMINISTRATEUR')")
    @PostMapping("/users/add")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        Optional<Role> r = roleRepository.findById(user.getId_role());
        user.setRole(r.get());
        user.setPassword(encoder.encode(user.getPassword().toString()));
        userRepository.save(user);
        System.out.println(user);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/users/liste")
    public ResponseEntity<?> listeUsers() {
        return ResponseEntity.ok(userRepository
                .findAll());
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok("utilisateur supprimé");
        } catch (Exception e) {
            return ResponseEntity.ok(new ErrorResponse("Suppression echouée"));
        }

    }

    @PutMapping("/users/update")
    public @ResponseBody
    User update(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/getusersbyusername/{username}")
    public @ResponseBody
    User searchByUsername(@PathVariable("username") String username){
        return userRepository.findByUsername(username);
    }

}