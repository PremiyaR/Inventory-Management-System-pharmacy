package com.pilot.inventory.service;

import com.pilot.inventory.dto.OrdersDto;
import com.pilot.inventory.exception.InsufficientQuantityException;
import com.pilot.inventory.exception.NoEntriesFound;
import com.pilot.inventory.mapper.ProductDtoMapper;
import com.pilot.inventory.mapper.UsersDtoMapper;
import com.pilot.inventory.model.Orders;
import com.pilot.inventory.model.Product;
import com.pilot.inventory.repository.OrdersRepository;
import com.pilot.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService{
    private final UsersDtoMapper usersDtoMapper;
    private final ProductDtoMapper productDtoMapper;

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;

    public OrdersServiceImpl(UsersDtoMapper usersDtoMapper, ProductDtoMapper productDtoMapper) {
        this.usersDtoMapper = usersDtoMapper;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public Orders addOrders(Orders orders) {
        Product product = orders.getProduct();
        int orderedQuantity = orders.getQuantity();
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new NoEntriesFound("Product not found"));

        int remainingQuantity = existingProduct.getQuantity() - orderedQuantity;

        if (remainingQuantity <= 0) {
            throw new InsufficientQuantityException();
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
    public List<OrdersDto> displayAllOrders() {
        List<OrdersDto> ordersList=ordersRepository.findByDeletedFalse()
                .stream()
                .map(orders->new OrdersDto(
                        usersDtoMapper.apply(orders.getUsers()),
                        productDtoMapper.apply(orders.getProduct()),
                        orders.getQuantity(),
                        orders.getTotalPrice(),
                        orders.getOrderTime()))
                .collect(Collectors.toList());;
        if(ordersList.isEmpty()){
            throw new NoEntriesFound();
        }
        return ordersList;
    }


}
