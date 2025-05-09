package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.user.UserRequestDTO;
import com.nagis.company.ecommerce.dto.user.UserResponseDTO;
import com.nagis.company.ecommerce.exception.BusinessException;
import com.nagis.company.ecommerce.exception.user.DuplicateEmailException;
import com.nagis.company.ecommerce.exception.user.InactiveUserException;
import com.nagis.company.ecommerce.exception.user.UserNotFoundException;
import com.nagis.company.ecommerce.mapper.user.UserMapper;
import com.nagis.company.ecommerce.model.Role;
import com.nagis.company.ecommerce.model.user.User;
import com.nagis.company.ecommerce.repository.RoleRepository;
import com.nagis.company.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

    public Page<UserResponseDTO> findAll(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toDTO);
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toDTO(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.email())) {
            throw new DuplicateEmailException(userDTO.email()); // Exceção de conflito
        }

        User user = userMapper.toEntity(userDTO);
        user.setRoles(fetchValidRoles(userDTO.roleIds())); // Pode lançar RoleNotFoundException

        return userMapper.toDTO(userRepository.save(user));
    }

    @Transactional
    public UserResponseDTO updatedUser(Long id, UserRequestDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        userMapper.updateFromDTO(userDTO, existingUser);
        existingUser.setRoles(fetchValidRoles(userDTO.roleIds()));

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Transactional
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado!");
        }
        userRepository.deleteById(id);
    }


    private Set<Role> fetchValidRoles(Set<Long> roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            // Exceção para role padrão não configurada
            return Set.of(roleRepository.findByName("ROLE_USER")
                    .orElseThrow(() -> new BusinessException("Role padrão não configurada", HttpStatus.INTERNAL_SERVER_ERROR)));
        }

        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        if (roles.size() != roleIds.size()) {
            // Exceção para IDs de roles inválidos
            throw new BusinessException("IDs de roles inválidos", HttpStatus.BAD_REQUEST);
        }
        return roles;
    }
}

