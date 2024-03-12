package com.pilot.inventory.service;

import com.pilot.inventory.model.entity.Categories;
import com.pilot.inventory.model.entity.Product;

import java.util.List;

public interface ProductService{
    public Product addProduct(Product product);
    public Product updateProduct(Product product);
    public String deleteProduct(int id);
    public List<Product> displayAll();
    public List<Product> findAllActiveProducts();
}
