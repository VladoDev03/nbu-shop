package org.example.exceptions;

public class InsufficientQuantityException extends Exception {
    public InsufficientQuantityException(String productName, int missingQuantity) {
        super("Insufficient quantity for product: " + productName + ". Missing quantity: " + missingQuantity);
    }
}
