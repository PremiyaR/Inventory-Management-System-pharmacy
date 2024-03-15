package com.pilot.inventory.repository;

import com.pilot.inventory.model.entity.Categories;
import com.pilot.inventory.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Integer> {
    Categories findByName(String name);
    List<Categories> findByDeletedFalse();
    Categories findByNameAndDeletedFalse(String name);
}
