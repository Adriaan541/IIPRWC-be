package com.example.iprwcbe.model.http;

import com.example.iprwcbe.model.database.UsersProducts;

import java.util.Set;

public class AuthResponse {
    private String email;
    private String accessToken;

    private String userId;

    private boolean admin;

    private Set<UsersProducts> usersProducts;

    public AuthResponse(String email, String accessToken, boolean admin, String id) { }

    public AuthResponse(String email, String accessToken, boolean admin, String userId, Set<UsersProducts> usersProducts) {
        this.email = email;
        this.accessToken = accessToken;
        this.admin = admin;
        this.userId = userId;
        this.usersProducts = usersProducts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<UsersProducts> getUsersProducts() {
        return usersProducts;
    }

    public void setUsersProducts(Set<UsersProducts> usersProducts) {
        this.usersProducts = usersProducts;
    }
}
