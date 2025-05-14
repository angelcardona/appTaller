package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "repairs")
public class Repair {
    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "repair_type",nullable = false)
    private String repairType;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name="labor_cost",nullable = false)
    private double laborCost;
    @Column(name = "mechanic_labor_percentage")
    private double mechanicLaborPercentage;
    @Column(name = "status",nullable = false)
    private String status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="vehicle_id",nullable = false)
    private Vehicle vehicle;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mechanic_id")
    private Mechanic mechanic;

    @Column(name = "start_date",nullable = false)
    private LocalDateTime startDate;
    @ManyToMany
    @JoinTable(
            name = "repair_spare_parts",
            joinColumns = @JoinColumn(name = "repair_id"),
            inverseJoinColumns = @JoinColumn(name = "spare_parts_id")
    )
    private List <SparePart> spareParts =new ArrayList<>();

    public Repair(String id, String repairType, String description, double laborCost, double mechanicLaborPercentage, String status, Vehicle vehicle, Mechanic mechanic,
                  LocalDateTime startDate, List<SparePart> spareParts) {
        this.id = id;
        this.repairType = repairType;
        this.description = description;
        this.laborCost = laborCost;
        this.mechanicLaborPercentage = mechanicLaborPercentage;
        this.status = status;
        this.vehicle = vehicle;
        this.mechanic = mechanic;
        this.startDate = startDate;
        this.spareParts = spareParts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRepairType() {
        return repairType;
    }

    public void setRepairType(String repairType) {
        this.repairType = repairType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(double laborCost) {
        this.laborCost = laborCost;
    }

    public double getMechanicLaborPercentage() {
        return mechanicLaborPercentage;
    }

    public void setMechanicLaborPercentage(double mechanicLaborPercentage) {
        this.mechanicLaborPercentage = mechanicLaborPercentage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public List<SparePart> getSpareParts() {
        return spareParts;
    }

    public void setSpareParts(List<SparePart> spareParts) {
        this.spareParts = spareParts;
    }
}
