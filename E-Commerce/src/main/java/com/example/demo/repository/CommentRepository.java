package com.example.demo.repository;

import com.example.demo.entity.comment.Comment;
import com.example.demo.entity.production.Product;
import com.example.demo.entity.user.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByProduct(Product product);
    List<Comment> findAllByProductOrderByIdDesc(Product product);
    List<Comment> findAllByClient(Client client);
    void deleteByProduct(Product product);
    int findRatingByProduct(Product product);
    List< Double > findRatingPercent(Product product);
    Long findQuantityRating(Product product);
}
