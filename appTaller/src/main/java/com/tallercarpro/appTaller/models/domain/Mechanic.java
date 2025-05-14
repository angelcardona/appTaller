package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="mechanics")
public class Mechanic {
    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "mechanic",nullable = false)
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "weekly_payment")
    private double weeklyPayment;

    public Mechanic(String id, String name, String phone, double weeklyPayment) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.weeklyPayment = weeklyPayment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getWeeklyPayment() {
        return weeklyPayment;
    }

    public void setWeeklyPayment(double weeklyPayment) {
        this.weeklyPayment = weeklyPayment;
    }
}
