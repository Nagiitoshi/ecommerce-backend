package com.nagis.company.ecommerce.mapper.user;

import com.nagis.company.ecommerce.dto.user.UserRequestDTO;
import com.nagis.company.ecommerce.dto.user.UserResponseDTO;
import com.nagis.company.ecommerce.model.Role;
import com.nagis.company.ecommerce.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true) // Será tratado manualmente
    User toEntity(UserRequestDTO dto);

    @Mapping(target = "roles", source = "roles", qualifiedByName = "mapRolesToNames")
    UserResponseDTO toDTO(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    void updateFromDTO(UserRequestDTO dto, @MappingTarget User user);

    @Named("mapRoleIdsToRoles")
    default Set<Role> mapRoleIdsToRoles(Set<Long> roleIds) {
        if (roleIds == null) return new HashSet<>();
        return roleIds.stream()
                .map(id -> {
                    Role role = new Role();
                    role.setId(id);
                    return role;
                })
                .collect(Collectors.toSet()); // Mudar para toSet()
    }
}

