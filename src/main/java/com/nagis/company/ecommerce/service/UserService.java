package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.user.UserRequestDTO;
import com.nagis.company.ecommerce.dto.user.UserResponseDTO;
import com.nagis.company.ecommerce.mapper.user.UserMapper;
import com.nagis.company.ecommerce.model.Role;
import com.nagis.company.ecommerce.model.User;
import com.nagis.company.ecommerce.repository.RoleRepository;
import com.nagis.company.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserMapper userMapper;

    // Search all users
    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    // Search user by Id
    public UserResponseDTO findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
        return userMapper.toDTO(user);
    }

    // Create new User
    @Transactional
    public UserResponseDTO create(UserRequestDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new RuntimeException("Email já cadastrado!");
        }

        User user = userMapper.toEntity(userDTO);
        user.setRoles(fetchValidRoles(userDTO.roleIds()));

        return userMapper.toDTO(userRepository.save(user));
    }

    // Update existing user
    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // Update only allowed fields
        userMapper.updateFromDTO(userDTO, existingUser);
        existingUser.setRoles(fetchValidRoles(userDTO.roleIds()));

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    // Delete User By Id
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    // Helper method to search for valid roles
    private Set<Role> fetchValidRoles(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Set.of(roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new RuntimeException("Role padrão não encontrada")));
        }
        return new HashSet<>(roleRepository.findAllById(roleIds));
    }
}
