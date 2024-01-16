package com.company.gamestore.model;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super("Resource Not Found: " + message);
    }
}
