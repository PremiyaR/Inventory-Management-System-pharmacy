package com.pilot.inventory.dto;

import java.time.LocalDate;

public record ProductRequestDto(
        String name,
        CategoryRequestDto categoryRequestDto,
        int quantity,
        double unitPrice,
        LocalDate expiryDate
) {
}
