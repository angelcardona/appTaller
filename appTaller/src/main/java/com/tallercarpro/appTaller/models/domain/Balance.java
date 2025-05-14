package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "start_date")
    private LocalDateTime startDate;
    @Column(name = "end_date")
    private LocalDateTime endDate;
    @Column(name = "efectivo_income")
    private double efectivoIncome;
    @Column(name = "banco_income")
    private double bancoIncome;

    private double incomes;
    private double expenses;
    private double balance;

    public Balance(String id, LocalDateTime startDate, LocalDateTime endDate, double efectivoIncome, double bancoIncome,
                   double incomes, double expenses, double balance) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.efectivoIncome = efectivoIncome;
        this.bancoIncome = bancoIncome;
        this.incomes = incomes;
        this.expenses = expenses;
        this.balance = balance;
    }

    public double getIncomes() {
        return incomes;
    }

    public void setIncomes(double incomes) {
        this.incomes = incomes;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public double getEfectivoIncome() {
        return efectivoIncome;
    }

    public void setEfectivoIncome(double efectivoIncome) {
        this.efectivoIncome = efectivoIncome;
    }

    public double getBancoIncome() {
        return bancoIncome;
    }

    public void setBancoIncome(double bancoIncome) {
        this.bancoIncome = bancoIncome;
    }
}
