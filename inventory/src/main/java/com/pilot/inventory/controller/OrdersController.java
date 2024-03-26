package com.pilot.inventory.controller;

import com.pilot.inventory.dto.OrdersDto;
import com.pilot.inventory.model.Orders;
import com.pilot.inventory.service.OrdersService;
import com.pilot.inventory.util.EndPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@RestController
@RequestMapping(EndPoint.ORDERS)
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
