package com.example.secondwork.model;

public class Offer {
    private long id;
    private String propertyDescription;
    private double price;
    private String agentName;

    public Offer(long id, String propertyDescription, double price, String agentName) {
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

    public void setId(long id) {
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
