/*
 * Author Name:
 * Date: 12/14/2022
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.model;

public class JwtRequset {
    String username;
    String password;

    public JwtRequset() {
    }

    public JwtRequset(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public JwtRequset setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public JwtRequset setPassword(String password) {
        this.password = password;
        return this;
    }
}
