package com.pilot.inventory.service;

import com.pilot.inventory.dto.ProductDto;
import com.pilot.inventory.dto.ProductRequestDto;
import com.pilot.inventory.model.Product;

import java.util.List;

public interface ProductService{
    public Product addProduct(ProductRequestDto productRequestDto);
    public Product updateProduct(Product product);
    public String deleteProduct(int id);
    public List<ProductDto> findAllActiveProducts();
    public void deleteProductIfQuantityZero(Product product);
}
