package com.example.demo.service.Impl;

import com.example.demo.production.Category;
import com.example.demo.production.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {

        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findOne(long id) {
        return productRepository.findById(id);
    }


    @Override
    public List<Product> search(String q) {

        return productRepository.findByNameContaining(q);
    }

    @Override
    public void save(Product pro) {
        productRepository.save(pro);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Override
    public List<Product> findProductByCategory(Category category) {
        return productRepository.findProductByCategory(category);
    }
}
