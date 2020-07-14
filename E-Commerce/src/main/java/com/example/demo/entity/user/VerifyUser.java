package com.example.demo.entity.user;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Verify")
public class VerifyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;

    private boolean active = true;

    @Column(name = "user_email")
    private String email;

    private LocalDateTime timeCreate;
    private LocalDateTime timeExpired;

    public VerifyUser(String email, String code, LocalDateTime timeCreate, LocalDateTime timeExpired){
        this.email = email;
        this.code = code;
        this.timeCreate = timeCreate;
        this.timeExpired = timeExpired;
    }

    public VerifyUser(String email, String code, LocalDateTime timeCreate){
        this.email = email;
        this.code = code;
        this.timeCreate = timeCreate;
        this.timeExpired = timeCreate.plusMinutes(5);
    }

    public VerifyUser(){
        this.timeExpired = LocalDateTime.now();
        this.timeExpired = timeCreate.plusMinutes(5);
    }

    public VerifyUser(String email, String code){
        this.email = email;
        this.code = code;
        this.timeCreate = LocalDateTime.now();
        this.timeExpired = LocalDateTime.now().plusMinutes(5);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }

    public LocalDateTime getTimeExpired() {
        return timeExpired;
    }

    public void setTimeExpired(LocalDateTime timeExpired) {
        this.timeExpired = timeExpired;
    }
}
