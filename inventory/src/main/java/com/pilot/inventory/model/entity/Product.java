package com.pilot.inventory.model.entity;

import com.pilot.inventory.model.dto.CategoryDto;
import com.pilot.inventory.model.entity.Categories;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.time.LocalDate;


@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String name;

    @Transient
    private CategoryDto categoryDto;

    @ManyToOne
    @JoinColumn(nullable = false,name = "category_id")
    private Categories categories;


    @PositiveOrZero(message = "It can be only zero or positive")
    private int quantity;

    @Column(name = "unit_price")
    @Positive(message = "Prices can only be positive")
    private double unitPrice;

    @Column(name = "expiry_date")
    @FutureOrPresent(message = "Expiry date must be in the present or future")
    private LocalDate expiryDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Product(int id, String name, CategoryDto categoryDto, Categories categories, int quantity, double unitPrice, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.categoryDto = categoryDto;
        this.categories = categories;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.expiryDate = expiryDate;
    }

    public Product() {
    }
}
