package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.CategoryDto;
import com.pilot.inventory.model.Categories;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDtoMapper implements Function<Categories, CategoryDto> {
    @Override
    public CategoryDto apply(Categories categories) {
        return new CategoryDto(
                categories.getId(),
                categories.getName()
        );
    }
}
