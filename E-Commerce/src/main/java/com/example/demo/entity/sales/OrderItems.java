package com.example.demo.entity.sales;

import com.example.demo.entity.production.Product;

import javax.persistence.*;

@Entity
@Table(name = "orderDetail")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderDetail_id")
    private long id;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "priceAll")
    private long priceAll;

    @Column(name = "discount")
    private long discount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "product_id", nullable = false)
    private Product products;

    public OrderItems(){}
    public OrderItems(long quantity, long priceAll, long discount,Order order, Product products){
        super();
        this.quantity = quantity;
        this.priceAll = priceAll;
        this.discount = discount;
        this.order = order;
        this.products = products;
    }

    public OrderItems(long quantity, long priceAll, Order order, Product products){
        this.quantity = quantity;
        this.priceAll = priceAll;
        this.discount = Long.valueOf(0);
        this.order = order;
        this.products = products;
    }

    public OrderItems(long quantity, Order order, Product products){
        this.quantity = quantity;
        this.priceAll = quantity*products.getPrice();
        this.discount = Long.valueOf(0);
        this.order = order;
        this.products = products;
    }

    public OrderItems(long quantity, Order order, Product products, long discount){
        this.quantity = quantity;
        this.priceAll = quantity*products.getPrice()*(100-discount);
        this.discount = discount;
        this.order = order;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getPriceAll() {
        return priceAll;
    }

    public void setPriceAll(long priceAll) {
        this.priceAll = priceAll;
    }

    public long getDiscount() {
        return discount;
    }

    public void setDiscount(long discount) {
        this.discount = discount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProducts() {
        return products;
    }

    public void setProducts(Product products) {
        this.products = products;
    }
}
