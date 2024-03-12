package com.pilot.inventory.service;

import com.pilot.inventory.exception.DuplicateName;
import com.pilot.inventory.exception.EntryAlreadyExists;
import com.pilot.inventory.exception.ItemAlreadyExistsException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Orders;
import com.pilot.inventory.model.entity.Product;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {

        Product existingProducts= productRepository.findByName(product.getName());

        if(existingProducts!=null)
        {
            throw new DuplicateName();
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product updatedProduct) {
        Product existingProduct = productRepository.findById(updatedProduct.getId())
                .orElseThrow(() -> new ItemAlreadyExistsException());

        if(existingProduct.getId()==updatedProduct.getId())
        {
            throw new EntryAlreadyExists();
        }
        existingProduct.setName(updatedProduct.getName());
        return productRepository.save(existingProduct);
    }

    @Override
    public String deleteProduct(int id) {
        String status=null;
        Optional<Product> optional=productRepository.findById(id);
        if(optional.isPresent())
        {
            productRepository.deleteById(id);
            status="Product deleted";
        }
        else {
            status="Product not deleted,Please check your id is valid";
        }
        return status;
    }

    @Override
    public List<Product> displayAll() {
        List<Product> products=productRepository.findAll();
        if(products.isEmpty()){
            throw new NoEntriesFound();
        }
        return products;
    }

    @Override
    public List<Product> findAllActiveProducts() {
        List<Product> productList=productRepository.findByDeletedFalse();
        if(productList.isEmpty()){
            throw new NoEntriesFound();
        }
        return productList;
    }
}
