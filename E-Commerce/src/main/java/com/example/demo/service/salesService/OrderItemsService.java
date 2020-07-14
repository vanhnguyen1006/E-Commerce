package com.example.demo.service.salesService;

import com.example.demo.entity.production.Product;

import com.example.demo.entity.sales.Order;
import java.util.List;

public interface OrderItemsService {
    List<Product> findAllProductsByOrder(Order order);
    List<Order> findAllOrderByProducts(Product product);
    Long findQuantityByProducts(Product product);
    Long findPriceAllByOrder(Order order);
    void deleteByProducts(Product product);
}
