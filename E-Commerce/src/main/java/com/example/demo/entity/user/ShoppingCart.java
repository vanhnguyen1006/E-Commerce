package com.example.demo.entity.user;

import com.example.demo.entity.production.Product;

import javax.persistence.*;

@Entity
@Table(name = "Shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", nullable = false)
    private Client client;

    private boolean active;

    public boolean isActive() {return active;}
    public void setActive(boolean active) { this.active = active; }

    private int quantity;

    public ShoppingCart(){}
    public ShoppingCart(Product product, Client client, int quantity){
        this.product = product;
        this.client = client;
        this.quantity = quantity;
        this.active = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
