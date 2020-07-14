package com.example.demo.entity.user;

import com.example.demo.entity.comment.Comment;
import com.example.demo.entity.sales.Order;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.*;

@Entity
@Table(name = "Clients")
public class Client implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name="";

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<ShoppingCart> shoppingCarts;

    private int age;

    @NotBlank(message = "Email is mandatory")
    private String email="";

    @NotBlank(message = "Phone is mandatory")
    private String phone="";

    private String userName = "null";

    private String password;

    @NotBlank(message = "Street is mandatory")
    private String street="";

    @NotBlank(message = "City is mandatory")
    private String city="";

    private String district="";

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinTable(name = "user_role", joinColumns = {
            @JoinColumn(name = "user_id", referencedColumnName = "id")
    }, inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private Set<Order> orders;

    public Set<Role> getRoles() {return roles; }
    public void setRoles(Set<Role> roles) {this.roles = roles; }
    public void addRole(Role role) {this.roles.add(role); }

    private boolean active = true;
    private boolean enable = true;
    private boolean block = true;
    private boolean expired = true;

    public boolean isActive() {return active; }
    public void setActive(boolean active) {this.active = active; }

    public Client(boolean active, String name, int age, String email, String phone,
                  String userName, String password, String street, String city){
        this.active = active;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.street = street;
        this.city = city;
    }

    public Client(String name, int age, String email, String phone, String street, String city){
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
    }

    public Client(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public Client(String userName, String password, Set<Role> roles){
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public Client(){}

    public long getId() {
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(Set<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isBlock() {
        return block;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public String longToString(Long a){ return a.toString(); }
    public String intToString(int a){ return String.valueOf(a);}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List< GrantedAuthority > list = new ArrayList<>();
        for (Role r: roles){
            list.add(new SimpleGrantedAuthority(r.getName()));
        }
        return list;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return block;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return expired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }
}
