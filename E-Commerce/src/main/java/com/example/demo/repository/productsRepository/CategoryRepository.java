package com.example.demo.repository.productsRepository;

import com.example.demo.entity.production.Category;
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
