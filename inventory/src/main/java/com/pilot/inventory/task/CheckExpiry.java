package com.pilot.inventory.task;

import com.pilot.inventory.model.Product;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckExpiry {
    @Autowired
    private ProductRepository productRepository;

    @Scheduled(fixedRate = 100)
    public void expiryDateChecker(){
        LocalDate currentDate = LocalDate.now();

        List<Product> expiredProducts = productRepository.findByExpiryDateBeforeAndDeletedFalse(currentDate);
        System.out.println("No product available");
        for (Product product : expiredProducts) {
            System.out.println(product);
            LocalDate expiryDate = product.getExpiryDate();
            if (currentDate.isEqual(expiryDate) || currentDate.isAfter(expiryDate)) {
                product.setDeleted(true);
                System.out.println(product.getName() + " has been removed due to expiry");
                productRepository.save(product);
            }
        }
    }
}
