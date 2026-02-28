package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "currency_full")
    private String currencyFull;

    @Column(name = "currency_short")
    private String currencyShort;

    public Currency() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyFull() {
        return currencyFull;
    }

    public void setCurrencyFull(String currencyFull) {
        this.currencyFull = currencyFull;
    }

    public String getCurrencyShort() {
        return currencyShort;
    }

    public void setCurrencyShort(String currencyShort) {
        this.currencyShort = currencyShort;
    }
}