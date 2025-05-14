package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "supplier_invoices")
public class SupplierInvoice {

    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
   @Column(name="date")
    private LocalDateTime date;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "supplier_id")
   private Supplier supplier;
   @Column(name = "total",nullable = false)
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vehicle_id")
   private Vehicle vehicle;
   private double total;
   @Column(name = "description",nullable = false)
   private String description;
   @Column(name = "paid",nullable = false)
   private boolean paid;


    public SupplierInvoice(String id, LocalDateTime date, Supplier supplier, Vehicle vehicle, double total,
                           String description, boolean paid) {
        this.id = id;
        this.date = date;
        this.supplier = supplier;
        this.vehicle = vehicle;
        this.total = total;
        this.description = description;
        this.paid = paid;
    }

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

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPaid() {
        return paid;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
