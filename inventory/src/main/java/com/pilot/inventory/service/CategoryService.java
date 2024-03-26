package com.pilot.inventory.service;

import com.pilot.inventory.dto.CategoryDto;
import com.pilot.inventory.model.Categories;

import java.util.List;


public interface CategoryService {
    public Categories addCategory(Categories categories);
    public Categories updateCategory(Categories categories);
    public String deleteCategory(int id);
    public List<CategoryDto> findAllActiveCategories();
}
