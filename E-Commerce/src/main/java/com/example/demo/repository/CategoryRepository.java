package com.example.demo.repository;

import com.example.demo.production.Category;
import com.example.demo.production.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends PagingAndSortingRepository<Category, Long> {
    Optional<Category> findByName(String name);

    List<Category> findByNameContaining(String p);

    List<Category> findAll();

}
