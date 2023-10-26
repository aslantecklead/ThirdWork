package com.example.secondwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "estate")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Field 'Address' can not be blank!")
    private String address;

    @NotNull(message = "Field 'Bedrooms' can not be blank!")
    @Min(value = 1, message = "Bedrooms must be at least 1")
    private int bedrooms;

    @NotNull(message = "Field 'Bathrooms' can not be blank!")
    @Min(value = 1, message = "Bathrooms must be at least 1")
    private int bathrooms;

    @NotNull(message = "Field 'Price' can not be blank")
    private Double price;

    public Estate(int id, String address, int bedrooms, int bathrooms, double price) {
        this.id = id;
        this.address = address;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.price = price;
    }

    public Estate() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}