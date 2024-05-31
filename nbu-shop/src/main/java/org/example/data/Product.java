package org.example.data;

import org.example.enums.ProductCategory;

import java.time.LocalDate;

public class Product {
    private long id;
    private String name;
    private double purchasePrice;
    private ProductCategory category;
    private LocalDate expiryDate;
    private int quantity;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }
}
