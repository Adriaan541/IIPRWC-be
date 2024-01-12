package com.example.iprwcbe.model.database;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsersProductsId implements Serializable {
    private String userId;

    private String productId;

    public UsersProductsId() {}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsersProductsId)) return false;
        UsersProductsId that = (UsersProductsId) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getProductId(), that.getProductId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getProductId());
    }
}
