/*
 * Author Name:
 * Date: 5/26/2024
 * Created With: IntelliJ IDEA Community Edition
 */


package com.exam.example.examportalproject.controller;
import java.time.Instant;
public class ApiResponse <T>{
    private String status;
    private String message;
    private T data;
    private Instant timestamp;

    public ApiResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }

    public String getStatus() {
        return status;
    }

    public ApiResponse<T> setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ApiResponse<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public ApiResponse<T> setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}
