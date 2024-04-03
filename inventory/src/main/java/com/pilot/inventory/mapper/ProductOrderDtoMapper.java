package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.ProductOrderDto;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.Orders;
import com.pilot.inventory.model.Product;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductOrderDtoMapper implements Function<ProductOrderDto, Product> {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product apply(ProductOrderDto productOrderDto) {
        Product product=productRepository.findByName(productOrderDto.name());

        Product product1=new Product();
        product1.setCategories(product.getCategories());
        product1.setExpiryDate(product.getExpiryDate());
        product1.setQuantity(product.getQuantity());
        product1.setName(product.getName());
        product1.setUnitPrice(product.getUnitPrice());
        return product1;
    }
}
