package com.pilot.inventory.controller;

import com.pilot.inventory.dto.CategoryDto;
import com.pilot.inventory.model.Categories;
import com.pilot.inventory.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<String> addCategory(@RequestBody Categories categories)
    {
        Categories savedCategories=categoryService.addCategory(categories);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateCategory(@RequestBody Categories categories)
    {
        Categories updatedcategories=categoryService.updateCategory(categories);
        return new ResponseEntity<>("Updated successfully",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable int id)
    {
        return new ResponseEntity<>(categoryService.deleteCategory(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> displayAllCategories()
    {
        List<CategoryDto> categories=categoryService.findAllActiveCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
}
