package com.example.demo.service.salesService;

import com.example.demo.entity.sales.Order;
import com.example.demo.entity.user.Client;

import java.util.List;

public interface OrderService {
    List<Order> findAllByClient(Client client);
    List<Order> findAllByStatus(String str);
    void deleteOrderById(Long id);
}
