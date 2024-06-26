package org.example.data;

import org.example.enums.ProductCategory;

import java.time.LocalDate;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private double purchasePrice;
    private ProductCategory category;
    private LocalDate expiryDate;
    private int quantity;

    public Product(String name, double purchasePrice, ProductCategory category, LocalDate expiryDate, int quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public Product(String id, String name, double purchasePrice, ProductCategory category, LocalDate expiryDate, int quantity) {
        this.id = id;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.category = category;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
    }

    public String getId() {
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
        if (getCategory() == ProductCategory.FOOD) {
            return LocalDate.now().isAfter(expiryDate);
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(getId()).append(System.lineSeparator());
        sb.append("Name: ").append(getName()).append(System.lineSeparator());
        sb.append("Quantity: ").append(getQuantity()).append(System.lineSeparator());
        sb.append("Purchase price: ").append(getPurchasePrice()).append(System.lineSeparator());

        if (category.equals(ProductCategory.FOOD)) {
            sb.append("Expiry date: ").append(getExpiryDate()).append(System.lineSeparator());
            sb.append("Is expired: ").append(getIsExpired()).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
