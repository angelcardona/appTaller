package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table(name="clients")
public class Client {
    @Id
    @Column(name="id")
    private String id= UUID.randomUUID().toString();
    @Column(name="name",nullable = false)
    private String name;
    @Column(name="phone",nullable = false)
    private String phone;
    @Column(name="Identification")
    private String identification;

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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public Client(String id, String name, String phone, String identification) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.identification = identification;
    }
}
