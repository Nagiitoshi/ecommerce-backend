package com.nagis.company.ecommerce.dto.user;

import com.nagis.company.ecommerce.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserRequestDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6) String password,
        @NotBlank @Pattern(regexp = "^\\d{11}$") String contactNumber,
        Address address,
        Set<Long> roleIds // IDs das roles a serem vinculadas
) {}
