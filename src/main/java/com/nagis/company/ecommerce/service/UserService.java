package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.model.User;
import com.nagis.company.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create user if there is no existing email
    @Transactional
    public User createUser(User user){
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()){
            throw new IllegalArgumentException("E-mail já está em uso!!!");
        }

        return userRepository.save(user);
    }

    // Search user by id
    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    // Update user information
    @Transactional
    public User updatedUser(Long id, User updateUser){
        Optional<User> existingUser = userRepository.findById(id);

        if (existingUser.isEmpty()){
            throw new IllegalArgumentException("Usário não Encontrado!!!");
        }

        User user = existingUser.get();
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        user.setPassword(updateUser.getPassword());
        user.setAddress(updateUser.getAddress());
        user.setContactNumber(updateUser.getContactNumber());

        return userRepository.save(user);
    }

    // Delete User
    @Transactional
    public void deleteUSer(Long id){
        userRepository.deleteById(id);
    }
}
