package com.pilot.inventory.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Entity
@Table(name="category")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //not auto-generated
    private int id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private boolean deleted;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Categories(int id, String name, boolean deleted) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
    }

    public Categories() {
    }
}
