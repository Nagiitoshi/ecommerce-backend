package com.nagis.company.ecommerce.mapper.role;

import com.nagis.company.ecommerce.dto.role.RoleRequestDTO;
import com.nagis.company.ecommerce.dto.role.RoleResponseDTO;
import com.nagis.company.ecommerce.model.Role;
import com.nagis.company.ecommerce.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "users", ignore = true)
    Role toEntity(RoleRequestDTO dto);

    @Mapping(target = "userIds", source = "users", qualifiedByName = "mapUsersToIds")
    RoleResponseDTO toDTO(Role role);

    @Named("mapUsersToIds")
    default Set<Long> mapUsersToIds(Set<User> users) {
        return users.stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }
}
