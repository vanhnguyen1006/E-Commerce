package com.example.demo.service.productsService;

import com.example.demo.entity.production.Category;
import com.example.demo.entity.production.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {
    Iterable<Product> findAll();

    Optional<Product> findOne(long id);

    List<Product> search(String q);

    void save(Product product);

    void delete(Product product);

    List<Product> findProductByCategory(Category category);
}
