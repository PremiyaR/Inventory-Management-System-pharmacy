package com.pilot.inventory.controller;

import com.pilot.inventory.dto.ProductDto;
import com.pilot.inventory.dto.ProductRequestDto;
import com.pilot.inventory.model.Product;
import com.pilot.inventory.service.ProductService;
import com.pilot.inventory.util.EndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping(EndPoint.PRODUCTS)
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductRequestDto productRequestDto)
    {
        productService.addProduct(productRequestDto);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<String> updateProduct(@RequestBody Product product)
    {
        productService.updateProduct(product);
        return new ResponseEntity<>("Update successful",HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
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
