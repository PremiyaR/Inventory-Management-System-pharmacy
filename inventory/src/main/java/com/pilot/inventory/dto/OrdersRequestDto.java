package com.pilot.inventory.dto;

import java.time.LocalDateTime;

public record OrdersRequestDto(
        UsersRequestDto usersRequestDto,
        ProductOrderDto productOrderDto,
        int quantity,
        double totalPrice,
        LocalDateTime orderTime
) {
}
