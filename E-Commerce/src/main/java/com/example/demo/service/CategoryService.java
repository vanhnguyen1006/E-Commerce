package com.example.demo.service;

import com.example.demo.production.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService{
    List<Category> findAll();

    Optional<Category> findOne(long id);

    List<Category> search(String q);

    void save(Category category);

    void delete(Category category);

}
