package com.pilot.inventory.service;


import com.pilot.inventory.exception.ItemAlreadyExistsException;
import com.pilot.inventory.exception.DuplicateName;
import com.pilot.inventory.exception.EntryAlreadyExists;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Categories;
import com.pilot.inventory.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Categories addCategory(Categories categories) {
       Categories existingCategories= categoryRepository.findByName(categories.getName());

       if(existingCategories!=null)
       {
           throw new DuplicateName();
       }
       return categoryRepository.save(categories);

    }

    @Override
    public Categories updateCategory(Categories updatedCategory) {
        Categories existingCategory = categoryRepository.findById(updatedCategory.getId())
                .orElseThrow(() -> new ItemAlreadyExistsException());

        if (!existingCategory.getName().equals(updatedCategory.getName())) {
            Categories existingByName = categoryRepository.findByNameAndDeletedFalse(updatedCategory.getName());
            if (existingByName != null) {
                throw new ItemAlreadyExistsException("Category with name " + updatedCategory.getName() + " already exists");
            }
        }

        if (existingCategory.isDeleted()) {
            throw new NoEntriesFound("Category is soft deleted");
        }

        if(existingCategory.getName().equals((updatedCategory.getName()))){
            throw new EntryAlreadyExists();
        }

        existingCategory.setName(updatedCategory.getName());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public String deleteCategory(int id) {

        Optional<Categories> optional=categoryRepository.findById(id);
        if(optional.isPresent())
        {
            Categories categories=optional.get();
            categories.setDeleted(true);
            categoryRepository.save(categories);
            return "Category deleted";
        }
        else {
            return "Category Not Found";
        }

    }

    @Override
    public List<Categories> displayAllCategories() {
        List<Categories> categories=categoryRepository.findAll();
        if(categories.isEmpty()){
            throw new NoEntriesFound();
        }
        return categories;
    }

    @Override
    public List<Categories> findAllActiveCategories() {
        List<Categories> categoriesList= categoryRepository.findByDeletedFalse();
        if(categoriesList.isEmpty()){
            throw new NoEntriesFound();
        }
        return categoriesList;
    }
}
