package com.example.secondwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "deal")
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Field 'Property' can not be blank!")
    private String property;
    @NotBlank(message = "Field 'Price' can not be blank!")
    private double price;
    @NotBlank(message = "Field 'Date' can not be blank!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public Deal(int id, String property, double price, Date date) {
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

    public void setId(int id) {
        this.id = id;
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
