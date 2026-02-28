package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_veriety")
public class OrderVeriety {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "veriety")
    private String veriety;

    public OrderVeriety() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVeriety() {
        return veriety;
    }

    public void setVeriety(String veriety) {
        this.veriety = veriety;
    }
}