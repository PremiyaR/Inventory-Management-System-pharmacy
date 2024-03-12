package com.pilot.inventory.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Generated;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @Column(name = "contact_number", unique = true)
    @Positive(message = "Contact Number must be positive")
    private int contactNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Users(int id, String name, int contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public Users() {
    }
}
