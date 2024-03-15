package com.pilot.inventory.service;

import com.pilot.inventory.exception.InsufficientQuantityException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.model.entity.Orders;
import com.pilot.inventory.model.entity.Product;
import com.pilot.inventory.model.entity.Users;
import com.pilot.inventory.repository.OrdersRepository;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImpl implements OrdersService{
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public Orders addOrders(Orders orders) {
        Product product = orders.getProduct();
        int orderedQuantity = orders.getQuantity();

        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new NoEntriesFound("Product not found"));

        int remainingQuantity = existingProduct.getQuantity() - orderedQuantity;

        if (remainingQuantity <= 0) {
            throw new InsufficientQuantityException("Insufficient quantity available for product: " + existingProduct.getName());
        }

        existingProduct.setQuantity(remainingQuantity);
        productService.deleteProductIfQuantityZero(existingProduct);
        productRepository.save(existingProduct);
        return ordersRepository.save(orders);
    }

    @Override
    public String deleteOrders(int id) {
        String status=null;
        Optional<Orders> optional=ordersRepository.findById(id);
        if(optional.isPresent())
        {
            Orders orders = optional.get();
            orders.setDeleted(true);
            ordersRepository.save(orders);
            return "Order deleted";
        }
        else {
            status="Order not deleted,Please check your id is valid";
        }
        return status;
    }

    @Override
    public List<Orders> displayAllOrders() {
        List<Orders> ordersList=ordersRepository.findByDeletedFalse();
        if(ordersList.isEmpty()){
            throw new NoEntriesFound();
        }
        return ordersList;
    }


}
