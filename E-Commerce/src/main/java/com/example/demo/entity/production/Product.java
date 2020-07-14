package com.example.demo.entity.production;


import com.example.demo.entity.sales.OrderItems;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long id;

    @NotBlank(message = "Product name is mandatory")
    private String name;

    private String linkImg;
    private int year;
    private long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<OrderItems> orderDetails;
    

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getLinkImg() {
        return this.linkImg;
    }

    public void setLinkImg(String linkImg) {
        this.linkImg = linkImg;
    }

    public Product(){}
    public Product(String name, int year, long price, Category category, Brand brand, String linkImg){
        super();
        this.name = name;
        this.year = year;
        this.price = price;
        this.category = category;
        this.brand = brand;
        this.linkImg = linkImg;
    }



    public Set<OrderItems> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(Set<OrderItems> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
