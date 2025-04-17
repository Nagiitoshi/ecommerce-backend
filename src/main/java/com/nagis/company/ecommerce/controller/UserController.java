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

    // Create a new user
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createdUser =userService.createUser(user);

        return ResponseEntity.ok(createdUser);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    //
    @GetMapping("/search/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        Optional<User> user = userService.getUserById(id);

        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User updatedUser){
        try{
            User user = userService.updatedUser(id, updatedUser);

            return ResponseEntity.ok(user);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }
    }

    //
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        try {
            userService.deleteUSer(id);
            return ResponseEntity.noContent().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }
}
