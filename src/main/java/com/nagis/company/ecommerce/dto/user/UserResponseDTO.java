package com.nagis.company.ecommerce.dto.user;

import com.nagis.company.ecommerce.model.user.Address;

import java.util.Set;

public record UserResponseDTO(
        Long id,
        String name,
        String email,
        String contactNumber,
        Address address,
        Set<String> roles
) {}