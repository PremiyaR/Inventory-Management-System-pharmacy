package com.pilot.inventory.dto;

import java.time.LocalDateTime;

public record OrdersDto(
        UsersDto usersDto,
        ProductDto productDto,
        int quantity,
        double totalPrice,
        LocalDateTime orderTime
) {
}
