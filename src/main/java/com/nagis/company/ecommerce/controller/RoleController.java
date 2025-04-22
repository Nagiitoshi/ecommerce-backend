package com.nagis.company.ecommerce.controller;

import com.nagis.company.ecommerce.dto.role.RoleRequestDTO;
import com.nagis.company.ecommerce.dto.role.RoleResponseDTO;
import com.nagis.company.ecommerce.model.Role;
import com.nagis.company.ecommerce.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(@Valid @RequestBody RoleRequestDTO roleDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @PostMapping("/{roleId}/users")
    public ResponseEntity<RoleResponseDTO> addUsers(
            @PathVariable Long roleId,
            @RequestBody Set<Long> userIds) {
        return ResponseEntity.ok(roleService.addUsersToRole(roleId, userIds));
    }
}
