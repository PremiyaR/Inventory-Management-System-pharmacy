package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.ProductDto;
import com.pilot.inventory.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class ProductDtoMapper implements Function<Product, ProductDto> {
    private final CategoryDtoMapper categoryDtoMapper;
    public ProductDtoMapper(CategoryDtoMapper categoryDtoMapper) {
        this.categoryDtoMapper = categoryDtoMapper;
    }
    @Override
    public ProductDto apply(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                categoryDtoMapper.apply(product.getCategories()),
                product.getQuantity(),
                product.getUnitPrice(),
                product.getExpiryDate()
        );
    }
}
