//package com.example.demo.sales;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Table(name = "order")
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "order_id")
//    private long id;
//
//    @Column(name = "order_status")
//    private String status;
//
//    @Column(name = "order_date")
//    private String date;
//
//    @Column(name = "required_date")
//    private String required;
//
//    @Column(name = "shipped_date")
//    private String shipped;
//
//    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
//    private Set<OrderDetail> orderDetails;
//
//    public Order(){}
//    public Order(String status, String date, String required, String shipped){
//        super();
//        this.status = status;
//        this.date = date;
//        this.required = required;
//        this.shipped = shipped;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getRequired() {
//        return required;
//    }
//
//    public void setRequired(String required) {
//        this.required = required;
//    }
//
//    public String getShipped() {
//        return shipped;
//    }
//
//    public void setShipped(String shipped) {
//        this.shipped = shipped;
//    }
//
//    public Set<OrderDetail> getOrderDetails() {
//        return orderDetails;
//    }
//
//    public void setOrderDetails(Set<OrderDetail> orderDetails) {
//        this.orderDetails = orderDetails;
//    }
//}
