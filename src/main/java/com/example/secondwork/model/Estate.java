package com.example.secondwork.model;

public class Estate {
    private int id;
    private String address;
    private int bedrooms;
    private int bathrooms;
    private double price;

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