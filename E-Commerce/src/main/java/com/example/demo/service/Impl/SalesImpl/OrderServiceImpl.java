package com.example.demo.service.Impl.SalesImpl;

import com.example.demo.entity.sales.Order;
import com.example.demo.entity.sales.OrderItems;
import com.example.demo.entity.user.Client;
import com.example.demo.repository.salesRepository.OrderItemsRepository;
import com.example.demo.repository.salesRepository.OrderRepository;
import com.example.demo.service.salesService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemsRepository orderItemsRepository;

    @Override
    public List<Order> findAllByClient(Client client) {
        List<Order> value = new ArrayList<>();
        List<Order> list = (List<Order>) orderRepository.findAll();
        for (Order o: list){
            if (client.getId() == o.getClient().getId()){
                value.add(o);
            }
        }
        return value;
    }

    @Override
    public List<Order> findAllByStatus(String str) {
        List<Order> value = new ArrayList<>();
        List<Order> list = (List<Order>) orderRepository.findAll();
        for (Order o: list){
            if (str.equals(o.getStatus())){
                value.add(o);
            }
        }
        return value;
    }

    @Override
    public void deleteOrderById(Long id) {
        List<OrderItems> list = (List<OrderItems>) orderItemsRepository.findAll();
        for (OrderItems oi: list){
            if (oi.getOrder().getId() == id){
                orderItemsRepository.delete(oi);
            }
        }
        orderRepository.deleteById(id);
    }
}
