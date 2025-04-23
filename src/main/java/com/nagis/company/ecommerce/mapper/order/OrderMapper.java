package com.nagis.company.ecommerce.mapper.order;

import com.nagis.company.ecommerce.dto.order.OrderRequestDTO;
import com.nagis.company.ecommerce.dto.order.OrderResponseDTO;
import com.nagis.company.ecommerce.model.order.Order;
import com.nagis.company.ecommerce.model.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orderTrackingNumber", ignore = true)
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "customer", source = "customerId", qualifiedByName = "mapCustomerIdToUser")
    Order toEntity(OrderRequestDTO dto);

    @Mapping(target = "customerId", source = "customer.id")
    OrderResponseDTO toDTO(Order order);

    @Named("mapCustomerIdToUser")
    default User mapCustomerIdToUser(Long customerId) {
        if (customerId == null) return null;
        User user = new User();
        user.setId(customerId);
        return user;
    }
}
