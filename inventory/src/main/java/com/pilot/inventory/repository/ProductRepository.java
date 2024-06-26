package com.pilot.inventory.repository;

import com.pilot.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    public Product findByName(String name);
    List<Product> findByDeletedFalse();
    Optional<Product> findByIdAndDeletedFalse(int id);
    Product findByNameAndDeletedFalse(String name);
    List<Product> findByExpiryDateBeforeAndDeletedFalse(LocalDate localDate);
}
