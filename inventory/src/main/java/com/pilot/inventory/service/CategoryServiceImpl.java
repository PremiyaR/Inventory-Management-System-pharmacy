package com.pilot.inventory.service;

import com.pilot.inventory.exception.DuplicateName;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.dto.CategoryDto;
import com.pilot.inventory.model.Categories;
import com.pilot.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Categories addCategory(Categories categories) {
        Categories existingCategories = categoryRepository.findByName(categories.getName());

        if (existingCategories != null) {
            throw new DuplicateName();
        }
        return categoryRepository.save(categories);

    }

    @Override
    public Categories updateCategory(Categories updatedCategory) {
        Categories existingCategory = categoryRepository.findById(updatedCategory.getId())
                .orElseThrow(() -> new NoEntriesFound("Category not found"));

        existingCategory.setName(updatedCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(int id) {

        Optional<Categories> optional = categoryRepository.findById(id);
        if (optional.isPresent()) {
            Categories categories = optional.get();
            categories.setDeleted(true);
            categoryRepository.save(categories);
            return "Category deleted";
        } else {
            return "Category Not Found";
        }

    }

    @Override
    public List<CategoryDto> findAllActiveCategories() {
        List<CategoryDto> categoriesList = categoryRepository.findByDeletedFalse()
                .stream()
                .map(category->new CategoryDto(
                        category.getId(),
                        category.getName()))
                .collect(Collectors.toList());
        if (categoriesList.isEmpty()) {
            throw new NoEntriesFound();
        }
        return categoriesList;
    }
}
