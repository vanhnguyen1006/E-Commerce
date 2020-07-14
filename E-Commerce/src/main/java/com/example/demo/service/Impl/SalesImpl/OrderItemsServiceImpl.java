package com.example.demo.service.Impl.SalesImpl;

import com.example.demo.entity.production.Product;
import com.example.demo.repository.salesRepository.OrderItemsRepository;
import com.example.demo.entity.sales.Order;
import com.example.demo.entity.sales.OrderItems;
import com.example.demo.service.salesService.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class OrderItemsServiceImpl implements OrderItemsService {
    @Autowired
    private OrderItemsRepository orderItemsRepository;


    @Override
    public List<Product> findAllProductsByOrder(Order order) {
        List<Product> value = new ArrayList<>();
        List<OrderItems> list = (List<OrderItems>) orderItemsRepository.findAll();
        for (OrderItems o: list){
            if (order.getId() == o.getOrder().getId()){
                value.add(o.getProducts());
            }
        }
        return value;
    }

    @Override
    public List<Order> findAllOrderByProducts(Product product) {
        List<Order> value = new ArrayList<>();
        List<OrderItems> list = (List<OrderItems>) orderItemsRepository.findAll();
        for (OrderItems o: list){
            if (product.getId() == o.getProducts().getId()){
                value.add(o.getOrder());
            }
        }
        return value;
    }

    @Override
    public Long findQuantityByProducts(Product product) {
        Long value = Long.valueOf(0);
        List<OrderItems> list = (List<OrderItems>) orderItemsRepository.findAll();
        for (OrderItems o: list){
            if (product.getId() == o.getProducts().getId()){
                value += o.getQuantity();
            }
        }
        return value;
    }

    @Override
    public Long findPriceAllByOrder(Order order) {
        Long value = Long.valueOf(0);
        List<OrderItems> list = (List<OrderItems>) orderItemsRepository.findAll();
        for (OrderItems o: list){
            if (order.getId() == o.getOrder().getId()){
                value += o.getPriceAll();
            }
        }
        return value;
    }

    @Override
    public void deleteByProducts(Product product) {

    }
}
