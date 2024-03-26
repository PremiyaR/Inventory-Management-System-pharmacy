package com.pilot.inventory.mapper;

import com.pilot.inventory.dto.ProductRequestDto;
import com.pilot.inventory.model.Product;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ProductRequestDtoMapper implements Function<ProductRequestDto, Product> {
    private final CategoryRequestDtoMapper categoryRequestDtoMapper;

    public ProductRequestDtoMapper(CategoryRequestDtoMapper categoryRequestDtoMapper) {
        this.categoryRequestDtoMapper = categoryRequestDtoMapper;
    }

    @Override
    public Product apply(ProductRequestDto productRequestDto) {
        Product product =new Product();
        product.setName(productRequestDto.name());
        product.setCategories(categoryRequestDtoMapper.apply(productRequestDto.categoryRequestDto()));
        product.setQuantity(productRequestDto.quantity());
        product.setUnitPrice(productRequestDto.unitPrice());
        product.setExpiryDate(productRequestDto.expiryDate());
        return product;
    }
}
