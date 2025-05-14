package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "spare_parts")
public class SparePart {
    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="description",nullable = false)
    private String description;
    @Column(name="cost",nullable = false)
    private double cost;
    @Column(name = "profit_percentage",nullable = false)
    private double profitPercentage;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_invoice_id")
    private SupplierInvoice supplierInvoice;

    public SparePart(String id, String name, String description, double cost, double profitPercentage, SupplierInvoice supplierInvoice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.profitPercentage = profitPercentage;
        this.supplierInvoice = supplierInvoice;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getProfitPercentage() {
        return profitPercentage;
    }

    public void setProfitPercentage(double profitPercentage) {
        this.profitPercentage = profitPercentage;
    }

    public SupplierInvoice getSupplierInvoice() {
        return supplierInvoice;
    }

    public void setSupplierInvoice(SupplierInvoice supplierInvoice) {
        this.supplierInvoice = supplierInvoice;
    }
}
