package com.example.secondwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offer")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Field 'Description' can not be blank!")
    private String propertyDescription;

    @NotNull(message = "Field 'Price' can not be null!")
    @Min(value = 0, message = "Field 'Price' must be a positive number!")
    private Double price = 0.0; // Устанавливаем значение по умолчанию на ноль

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
