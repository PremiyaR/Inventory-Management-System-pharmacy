package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.OrdersDto;
import com.pilot.inventory.model.Orders;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrdersDtoMapper implements Function<Orders, OrdersDto> {
    private final UsersDtoMapper usersDtoMapper;
    private final ProductDtoMapper productDtoMapper;

    public OrdersDtoMapper(UsersDtoMapper usersDtoMapper, ProductDtoMapper productDtoMapper) {
        this.usersDtoMapper = usersDtoMapper;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public OrdersDto apply(Orders orders) {
        return new OrdersDto(
                usersDtoMapper.apply(orders.getUsers()),
                productDtoMapper.apply(orders.getProduct()),
                orders.getQuantity(),
                orders.getTotalPrice(),
                orders.getOrderTime()
        );
    }
}
