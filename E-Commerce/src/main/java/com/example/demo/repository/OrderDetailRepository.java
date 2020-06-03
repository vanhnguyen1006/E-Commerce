package com.example.demo.repository;

import com.example.demo.sales.OrderDetail;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface OrderDetailRepository extends PagingAndSortingRepository<OrderDetail, Long> {

}
