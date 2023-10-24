package com.example.secondwork.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Deal {
    private long id;
    private String property;
    private double price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Deal(long id, String property, double price, Date date) {
        this.id = id;
        this.property = property;
        this.price = price;
        this.date = date;
    }

    public Deal() {

    }

    public long getId() {
        return id;
    }

    public String getProperty() {
        return property;
    }

    public double getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
