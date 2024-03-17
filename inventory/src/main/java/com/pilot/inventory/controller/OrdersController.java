package com.pilot.inventory.controller;

import com.pilot.inventory.dto.OrdersDto;
import com.pilot.inventory.model.Orders;
import com.pilot.inventory.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @PostMapping
    public ResponseEntity<String> addOrders(@RequestBody Orders orders)
    {
        Orders savedOrders=ordersService.addOrders(orders);
        return new ResponseEntity<>("Created successfully", HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrders(@PathVariable int id)
    {
        return new ResponseEntity<>(ordersService.deleteOrders(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrdersDto>> displayAllOrders()
    {
        List<OrdersDto> orders=ordersService.displayAllOrders();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
