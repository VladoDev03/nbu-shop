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

    public Product(long id, String name, double purchasePrice, ProductCategory category, LocalDate expiryDate, int quantity) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSellingPrice(double foodMarkup, double nonFoodMarkup, int daysBeforeExpiryForDiscount, double discountPercentage) {
        double markup = (category == ProductCategory.FOOD) ? foodMarkup : nonFoodMarkup;
        double sellingPrice = purchasePrice * (1 + markup / 100);
        if (expiryDate.isBefore(LocalDate.now().plusDays(daysBeforeExpiryForDiscount))) {
            sellingPrice *= (1 - discountPercentage / 100);
        }
        return sellingPrice;
    }

    public boolean getIsExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
