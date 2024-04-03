package com.pilot.inventory.dto;

import java.time.LocalDate;

public record ProductDto(
        int id,
        String name,
        CategoryDto categoryDto,
        int quantity,
        double unitPrice,
        LocalDate expiryDate
) {
}
