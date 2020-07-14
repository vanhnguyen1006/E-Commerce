package com.example.demo.repository.salesRepository;

import com.example.demo.entity.sales.Order;
import com.example.demo.entity.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
