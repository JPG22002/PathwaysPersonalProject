package com.company.gamestore.model;

public class InsufficientQuantityException extends RuntimeException {
    public InsufficientQuantityException(String message) {
        super("Insufficient Quantity: " + message);
    }
}
