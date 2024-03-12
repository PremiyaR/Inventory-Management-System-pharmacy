package com.pilot.inventory.service;

import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Orders;
import com.pilot.inventory.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class OrdersServiceImpl implements OrdersService{
    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public Orders addOrders(Orders orders) {
        return ordersRepository.save(orders);
    }


    @Override
    public String deleteOrders(int id) {
        String status=null;
        Optional<Orders> optional=ordersRepository.findById(id);
        if(optional.isPresent())
        {
            ordersRepository.deleteById(id);
            status="Product deleted";
        }
        else {
            status="Product not deleted,Please check your id is valid";
        }
        return status;
    }

    @Override
    public List<Orders> displayAllOrders() {
        List<Orders> orders=ordersRepository.findAll();
        if(orders.isEmpty()){
            throw new NoEntriesFound();
        }
        return orders;
    }
}
