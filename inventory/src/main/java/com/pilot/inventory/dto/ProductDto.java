package com.pilot.inventory.dto;

import com.pilot.inventory.model.Categories;

import java.time.LocalDate;

public record ProductDto(
        String name,
        CategoryDto categoryDto,
        int quantity,
        double unitPrice,
        LocalDate expiryDate
) {
}
