package com.example.org.error;

public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}