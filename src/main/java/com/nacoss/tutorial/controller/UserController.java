package com.nacoss.tutorial.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.nacoss.tutorial.model.User;
import com.nacoss.tutorial.model.repositories.UserRepository;
import com.nacoss.tutorial.model.requests.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/user")


public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userRepository.findAll();

        return users == null || users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<User> findByID(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.of(user);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);

//        07055743627
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        User user = new User();
        user.setUsername(userCreateRequest.getUsername());
        user.setFirstname(userCreateRequest.getFirstname());
        user.setLastname(userCreateRequest.getLastname());
        user.setOthername(userCreateRequest.getOthername());

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

}
