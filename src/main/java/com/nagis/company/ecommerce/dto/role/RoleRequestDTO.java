package com.nagis.company.ecommerce.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;



public record RoleRequestDTO(
        @NotBlank(message = "O nome da role é obrigatório")
        @Size(max = 50, message = "O nome deve ter no máximo 50 caracteres")
        String name
) {}

