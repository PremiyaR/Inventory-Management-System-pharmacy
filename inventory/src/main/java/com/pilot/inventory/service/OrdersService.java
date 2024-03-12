package com.pilot.inventory.service;

import com.pilot.inventory.model.entity.Categories;
import com.pilot.inventory.model.entity.Orders;

import java.util.List;

public interface OrdersService {
    public Orders addOrders(Orders orders);
    public String deleteOrders(int id);
    public List<Orders> displayAllOrders();
}
