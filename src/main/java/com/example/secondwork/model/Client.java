package com.example.secondwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Field 'Name' can not be blank!")
    private String name;
    @NotBlank(message = "Field 'Email' can not be blank!")
    private String email;
    @NotBlank(message = "Field 'Phone Number' can not be blank!")

    private String phonenumber;

    @Column(name = "budget")
    @NotNull
    @Min(value = 0, message = "Budget must be a positive number")
    private double budget;
    public Client(int id, String name, String email, String phoneNumber, double budget) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phonenumber = phoneNumber;
        this.budget = budget;
    }

    public Client(){}

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public double getBudget() {
        return budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhonenumber(String phoneNumber) {
        this.phonenumber = phoneNumber;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        id = id;
    }
}
