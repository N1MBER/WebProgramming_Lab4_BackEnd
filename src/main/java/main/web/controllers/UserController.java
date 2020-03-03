package main.web.controllers;

import main.web.entity.User;
import main.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            System.out.println(user.getUsername() + "- That's login already existed");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        System.out.println(user + " registered");
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @CrossOrigin
    @RequestMapping(value = "/login")
    public Principal user(Principal principal) {
        System.out.println(principal.getName() + " is logined");
        return principal;
    }
}

