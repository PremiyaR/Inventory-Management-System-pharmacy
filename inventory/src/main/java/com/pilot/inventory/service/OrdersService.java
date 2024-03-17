package com.pilot.inventory.service;

import com.pilot.inventory.dto.OrdersDto;
import com.pilot.inventory.model.Orders;

import java.util.List;

public interface OrdersService {
    public Orders addOrders(Orders orders);
    public String deleteOrders(int id);
    public List<OrdersDto> displayAllOrders();
}
