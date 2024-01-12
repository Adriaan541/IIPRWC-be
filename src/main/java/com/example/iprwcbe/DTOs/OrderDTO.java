package com.example.iprwcbe.DTOs;

import com.example.iprwcbe.model.database.OrderProduct;
import com.example.iprwcbe.model.database.User;

import java.util.Date;
import java.util.Set;

public class OrderDTO {
    private String id;
    private Date orderDate;
    private User user;
    private Set<OrderProduct> orderProducts;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(Set<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}
