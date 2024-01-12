package com.example.iprwcbe.model.database;

import javax.persistence.*;

@Entity
@Table(name = "users_products")
public class UsersProducts {
    @EmbeddedId
    private UsersProductsId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    private int quantity;

    public UsersProducts() {}

    public UsersProductsId getId() {
        return id;
    }

    public void setId(UsersProductsId id) {
        this.id = id;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

