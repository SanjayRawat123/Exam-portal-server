/*
 * Author Name:
 * Date: 12/14/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.model;

public class JwtRequest {
    String username;
    String password;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public JwtRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}
