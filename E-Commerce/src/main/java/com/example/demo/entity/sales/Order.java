package com.example.demo.entity.sales;

import com.example.demo.entity.user.Client;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @NotBlank(message = "Status is mandatory")
    @Column(name = "order_status")
    private String status;

    @Column(name = "order_date")
    private String date;

    @Column(name = "required_date")
    private String required = null;

    private String receiverName = null;
    private String receiverPhone = null;
    private String homeAddress = null;
    private String district = null;
    private String city = null;
    private long priceAllOrder;

    @Column(name = "shipped_date")
    private String shipped = null;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<OrderItems> orderItems;

    public Order(){}

    public Order(Client client, String date, String status, String required, String shipped,
                 String receiverName, String receiverPhone, String homeAddress, String district,
                 String city, long priceAllOrder){
        this.client =client;
        this.date = date;
        this.status = status;
        this.required = required;
        this.shipped = shipped;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.homeAddress = homeAddress;
        this.district = district;
        this.city = city;
        this.priceAllOrder = priceAllOrder;
    }

    public Order(String status, String date, String required, String shipped,
                 long priceAllOrder, Client client){
        this.client = client;
        this.status = status;
        this.date = date;
        this.required = required;
        this.shipped = shipped;
        this.priceAllOrder = priceAllOrder;
    }

    public Order(Client client, String required, long priceAllOrder){
        this.client = client;
        this.date = String.valueOf(LocalDate.now());
        this.status = "Waiting";
        this.required = required;
        this.shipped = "null";
        this.priceAllOrder = priceAllOrder;
    }

    public Order(Client client, long priceAllOrder){
        this.client = client;
        this.date = String.valueOf(LocalDate.now());
        this.status = "Waiting";
        this.required = String.valueOf(LocalDate.now().plusDays(7));
        this.shipped = "Not shipped yet";
        this.priceAllOrder = priceAllOrder;
    }

    public Order(Client client, long priceAllOrder, String receiverName, String receiverPhone,
                 String homeAddress, String district, String city){
        this.client = client;
        this.priceAllOrder = priceAllOrder;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.homeAddress = homeAddress;
        this.district = district;
        this.city = city;
        this.date = String.valueOf(LocalDate.now());
        this.status = "Waiting";
        this.required = String.valueOf(LocalDate.now().plusDays(7));
        this.shipped = "Not shipped yet";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getShipped() {
        return shipped;
    }

    public void setShipped(String shipped) {
        this.shipped = shipped;
    }

    public Set<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPriceAllOrder() {
        return priceAllOrder;
    }

    public void setPriceAllOrder(long priceAllOrder) {
        this.priceAllOrder = priceAllOrder;
    }
}
