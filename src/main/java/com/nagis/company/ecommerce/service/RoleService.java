package com.nagis.company.ecommerce.service;

import com.nagis.company.ecommerce.dto.role.RoleRequestDTO;
import com.nagis.company.ecommerce.dto.role.RoleResponseDTO;
import com.nagis.company.ecommerce.mapper.role.RoleMapper;
import com.nagis.company.ecommerce.model.Role;
import com.nagis.company.ecommerce.model.user.User;
import com.nagis.company.ecommerce.repository.RoleRepository;
import com.nagis.company.ecommerce.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private RoleMapper roleMapper;

    public RoleResponseDTO create(RoleRequestDTO roleDTO) {
        if (roleRepository.existsByName(roleDTO.name())) {
            throw new RuntimeException("Role já existe!");
        }
        Role role = roleMapper.toEntity(roleDTO);
        return roleMapper.toDTO(roleRepository.save(role));
    }

    public RoleResponseDTO findById(Long id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role não encontrada!"));
        return roleMapper.toDTO(role);
    }

    public List<RoleResponseDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDTO)
                .toList();
    }

    @Transactional
    public RoleResponseDTO addUsersToRole(Long roleId, Set<Long> userIds) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role não encontrada!"));

        Set<User> users = new HashSet<>(userRepository.findAllById(userIds));
        users.forEach(user -> user.getRoles().add(role));

        return roleMapper.toDTO(role);
    }
}
