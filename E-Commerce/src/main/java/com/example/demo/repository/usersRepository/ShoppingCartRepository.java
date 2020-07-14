package com.example.demo.repository.usersRepository;

import com.example.demo.entity.production.Product;
import com.example.demo.entity.user.Client;
import com.example.demo.entity.user.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartRepository, Long> {
    List<Product> findAllProductByClient(Client client);
    Long findPriceAllByClient(Client client);
    List<ShoppingCart> findAllByClient(Client client);
    List<ShoppingCart> findAllIsActiveByClient(Client client);
    List<ShoppingCart> findAllNoneActiveByClient(Client client);
    Optional<ShoppingCart> findByClientAndProduct(Client client, Product product);
    void deleteByProduct(Product product);
}
