package com.example.secondwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Field 'Description' can not be blank!")
    private String propertyDescription;
    @NotBlank(message = "Field 'Price' can not be blank!")
    private double price;
    @NotBlank(message = "Field 'Agent Name' can not be blank!")
    private String agentName;

    public Offer(int id, String propertyDescription, double price, String agentName) {
        this.id = id;
        this.propertyDescription = propertyDescription;
        this.price = price;
        this.agentName = agentName;
    }

    public Offer() {

    }

    public long getId() {
        return id;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public double getPrice() {
        return price;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }
}
