package com.example.demo.sales;

import com.example.demo.production.Product;
import sun.jvm.hotspot.ui.SAEditorPane;

import javax.persistence.*;

@Entity
@Table(name = "orderDetail")
public class OrderDetail {
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
    private Product product;

    public OrderDetail(){}
    public OrderDetail(long quantity, long priceAll, long discount, Order order, Product product){
        super();
        this.quantity = quantity;
        this.priceAll = priceAll;
        this.discount = discount;
        this.order = order;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
