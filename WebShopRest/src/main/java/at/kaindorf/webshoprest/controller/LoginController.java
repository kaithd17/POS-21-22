package at.kaindorf.webshoprest.controller;

import at.kaindorf.webshoprest.bl.Token;
import at.kaindorf.webshoprest.bl.User;
import at.kaindorf.webshoprest.database.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<User> login (@RequestBody User user) {
        Optional<User> dbUser = userRepository.findById(user.getMail());
        if (dbUser.isPresent() && dbUser.get().getPassword().equals(user.getPassword())) {

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Authorization", "Dummy");
            return ResponseEntity.ok().headers(httpHeaders).body(dbUser.get());
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
