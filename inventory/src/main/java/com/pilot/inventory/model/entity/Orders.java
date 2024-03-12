package com.pilot.inventory.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @Min(value= 1,message="Quantity must be atleast 1")
    private int quantity;

    @NotNull(message="Total price cannot be null")
    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "order_time")
    private LocalDateTime orderTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public Orders(int id, Users users, Product product, int quantity, double totalPrice, LocalDateTime orderTime) {
        this.id = id;
        this.users = users;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
    }

    public Orders() {
    }
}
