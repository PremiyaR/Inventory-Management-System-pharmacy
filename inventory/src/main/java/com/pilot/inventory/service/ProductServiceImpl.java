package com.pilot.inventory.service;

import com.pilot.inventory.exception.DuplicateName;
import com.pilot.inventory.exception.EntryAlreadyExists;
import com.pilot.inventory.exception.ItemAlreadyExistsException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Orders;
import com.pilot.inventory.model.entity.Product;
import com.pilot.inventory.model.entity.Users;
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
        Product existingProducts = productRepository.findById(updatedProduct.getId())
                .orElseThrow(() -> new ItemAlreadyExistsException());

        if (!existingProducts.getName().equals(updatedProduct.getName())) {
            Product existingByName = productRepository.findByNameAndDeletedFalse(updatedProduct.getName());
            if (existingByName != null) {
                throw new ItemAlreadyExistsException("Product with name " + updatedProduct.getName() + " already exists");
            }
        }

        if (existingProducts.isDeleted()) {
            throw new NoEntriesFound("Product is deleted");
        }

        if (existingProducts.getName().equals((updatedProduct.getName()))) {
            throw new EntryAlreadyExists();
        }

        existingProducts.setName(updatedProduct.getName());
        return productRepository.save(existingProducts);
    }

    @Override
    public String deleteProduct(int id) {
        Optional<Product> optional = productRepository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setDeleted(true);
            productRepository.save(product);
            return "Product deleted";
        } else {
            return "Product Not Found";
        }
    }

    @Override
    public List<Product> findAllActiveProducts() {
        List<Product> productList=productRepository.findByDeletedFalse();
        if(productList.isEmpty()){
            throw new NoEntriesFound();
        }
        return productList;
    }

    @Override
    public void deleteProductIfQuantityZero(Product product) {
        if(product.getQuantity()==0){
            product.setDeleted(true);
            productRepository.save(product);
        }
    }
}
