package com.tallercarpro.appTaller.models.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @Column(name = "id")
    private String id= UUID.randomUUID().toString();
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "contact")
    private String contact;

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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Supplier(String id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }
}
