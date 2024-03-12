package com.pilot.inventory.model.dto;

public class CategoryDto {
    public String name;

    public CategoryDto(String name) {
        this.name = name;
    }

    public CategoryDto()
    {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
