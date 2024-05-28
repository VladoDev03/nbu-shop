package org.example.data;

import org.example.enums.ProductCategory;

import java.time.LocalDate;

public class Product {
    private String id;
    private String name;
    private double purchasePrice;
    private ProductCategory category;
    private LocalDate expiryDate;
    private int quantity;
}
