package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="vehicles")
public class Vehicle {
    @Id

    @Column(name="id")
    private String id= UUID.randomUUID().toString();
    @Column(name="brand",nullable = false)
    private String brand;
    @Column(name="model",nullable = false)
    private String model;
    @Column(name="year",nullable = false)
    private int year;
    @Column(name="license_plate",nullable = false)
    private String licensePlate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="client_id",nullable = false)
    private Client client;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle(String id, String brand, String model, int year, String licensePlate, Client client) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.client = client;
    }
}
