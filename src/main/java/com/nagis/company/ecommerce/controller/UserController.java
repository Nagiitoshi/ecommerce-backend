package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.model.User;
import com.nagis.company.ecommerce.repository.UserRepository;
import com.nagis.company.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    // Search all products
    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    // Search product by id
    @GetMapping("/search/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser =userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }

    // Update user by id
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User updatedUser){
        try{
            User user = userService.updatedUser(id, updatedUser);

            return ResponseEntity.ok(user);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    // Delete user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id){
        try {
            userService.deleteUSer(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
