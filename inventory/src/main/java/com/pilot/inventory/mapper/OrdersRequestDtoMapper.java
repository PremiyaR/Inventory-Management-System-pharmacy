package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.OrdersRequestDto;
import com.pilot.inventory.model.Orders;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrdersRequestDtoMapper implements Function<OrdersRequestDto, Orders> {
    private final UsersRequestDtoMapper usersRequestDtoMapper;
    private final ProductOrderDtoMapper productOrderDtoMapper;

    public OrdersRequestDtoMapper(UsersRequestDtoMapper usersRequestDtoMapper, ProductOrderDtoMapper productOrderDtoMapper) {
        this.usersRequestDtoMapper = usersRequestDtoMapper;
        this.productOrderDtoMapper = productOrderDtoMapper;
    }

    @Override
    public Orders apply(OrdersRequestDto ordersRequestDto) {
        Orders orders=new Orders();
        orders.setUsers(usersRequestDtoMapper.apply(ordersRequestDto.usersRequestDto()));
        orders.setProduct(productOrderDtoMapper.apply(ordersRequestDto.productOrderDto()));
        orders.setQuantity(ordersRequestDto.quantity());
        orders.setTotalPrice(ordersRequestDto.totalPrice());
        orders.setOrderTime(ordersRequestDto.orderTime());
        return orders;
    }
}
