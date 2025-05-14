package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name="invoices")
public class Invoice {
    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "date",nullable = false)
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id",nullable = false)
    private Vehicle vehicle;
    @Column(name = "labor_cost",nullable = false)
    private Double laborCost;
    @Column(name="spare_part_cost",nullable = false)
    private Double sparePartCost;
    @Column(name = "other_costs")
    private double otherCost;
    @Column(name = "total")
    private double total;
    @Column(name = "is_vehicle_delivered",nullable = false)
    private boolean isVehicleDelivered;
    @Enumerated(EnumType.STRING)
    @Column(name="payment_method",nullable = false)
    private PaymentMethod paymentMethod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public double getSparePartCost() {
        return sparePartCost;
    }

    public void setSparePartCost(double sparePartCost) {
        this.sparePartCost = sparePartCost;
    }

    public double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(double otherCost) {
        this.otherCost = otherCost;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isVehicleDelivered() {
        return isVehicleDelivered;
    }

    public void setVehicleDelivered(boolean vehicleDelivered) {
        isVehicleDelivered = vehicleDelivered;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Invoice(String id, LocalDateTime date, Client client, Vehicle vehicle, double laborCost, double sparePartCost, double otherCost,
                   double total, boolean isVehicleDelivered, PaymentMethod paymentMethod) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.vehicle = vehicle;
        this.laborCost = laborCost;
        this.sparePartCost = sparePartCost;
        this.otherCost = otherCost;
        this.total = total;
        this.isVehicleDelivered = isVehicleDelivered;
        this.paymentMethod = paymentMethod;
    }
}
