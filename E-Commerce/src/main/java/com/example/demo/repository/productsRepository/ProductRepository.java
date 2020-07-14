package com.example.demo.repository.productsRepository;

import com.example.demo.entity.production.Category;
import com.example.demo.entity.production.Product;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

    List<Product> findByNameContaining(String q);
    List<Product> findProductByCategory(Category category);
}

