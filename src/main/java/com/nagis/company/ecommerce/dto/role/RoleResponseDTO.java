package com.nagis.company.ecommerce.dto.role;

import java.util.Set;

public record RoleResponseDTO(
        Long id,
        String name,
        Set<Long> userIds // IDs dos usuários associados
) {}