package com.pilot.inventory.repository;

import com.pilot.inventory.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    public List<Orders> findByDeletedFalse();
}
