package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.CategoryRequestDto;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.Categories;
import com.pilot.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryRequestDtoMapper implements Function<CategoryRequestDto,Categories> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Categories apply(CategoryRequestDto categoryRequestDto) {
        Categories category = categoryRepository.findByName(categoryRequestDto.name());

        if (category == null) {
            throw new NoEntriesFound("Category not found for name: " + categoryRequestDto.name());
        }
        Categories categories = new Categories();
        categories.setId(category.getId());
        categories.setName(category.getName());
        return categories;
    }
}
