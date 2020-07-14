package com.example.demo.entity.comment;

import com.example.demo.entity.production.Product;
import com.example.demo.entity.user.Client;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int rating = 0;

    protected String content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "clients"))
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "products"))
    private Product product;

    public Comment(){}
    public Comment(String str) {this.content = str; }
    public Comment(String content, Client client, Product product) {
        this.content = content;
        this.client = client;
        this.product = product;
    }
    public Comment(String content, Client client, Product product, int rating) {
        this.content = content;
        this.client = client;
        this.product = product;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String longToString(Long a) { return a.toString(); }
}
