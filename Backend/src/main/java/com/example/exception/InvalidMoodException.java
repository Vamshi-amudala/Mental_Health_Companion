package com.example.exception;

public class InvalidMoodException extends RuntimeException {
    public InvalidMoodException(String message) {
        super(message);
    }
}