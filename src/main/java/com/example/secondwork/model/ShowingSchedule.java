package com.example.secondwork.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ShowingSchedule {
    private long id;
    private String property;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private String clientName;

    public ShowingSchedule(long id, String property, Date date, String clientName) {
        this.id = id;
        this.property = property;
        this.date = date;
        this.clientName = clientName;
    }

    public ShowingSchedule() {

    }

    public long getId() {
        return id;
    }

    public String getProperty() {
        return property;
    }

    public Date getDate() {
        return date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}