package com.pilot.inventory.service;

import com.pilot.inventory.exception.InsufficientQuantityException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Orders;
import com.pilot.inventory.model.entity.Product;
import com.pilot.inventory.repository.OrdersRepository;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public Orders addOrders(Orders orders) {
        Product product = orders.getProduct();
        int orderedQuantity = orders.getQuantity();
        int remainingQuantity = product.getQuantity() - orderedQuantity;

        if (remainingQuantity < 0) {
            throw new InsufficientQuantityException("Insufficient quantity available for product: " + product.getName());
        }

        product.setQuantity(remainingQuantity);
        productRepository.save(product);

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
