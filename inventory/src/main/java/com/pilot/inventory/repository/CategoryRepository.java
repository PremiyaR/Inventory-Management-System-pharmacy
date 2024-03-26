package com.pilot.inventory.repository;

import com.pilot.inventory.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Integer> {
    Categories findByName(String name);
    List<Categories> findByDeletedFalse();
    Categories findByNameAndDeletedFalse(String name);
}
