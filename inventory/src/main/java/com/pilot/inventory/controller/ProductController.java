package com.pilot.inventory.controller;

import com.pilot.inventory.dto.ProductDto;
import com.pilot.inventory.model.Product;
import com.pilot.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product)
    {
        productService.updateProduct(product);
        return new ResponseEntity<>("Update successful",HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProduct(@PathVariable int id)
    {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Deleted successfully",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> displayAllProducts()
    {
        List<ProductDto> product=productService.findAllActiveProducts();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
