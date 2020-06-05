package com.example.demo.repository;

import com.example.demo.production.Category;
import com.example.demo.production.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByNameContaining(String q);
    List<Product> findProductByCategory(Category category);
}

