package org.example.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    public Receipt() {
        items = new ArrayList<>();
    }

    public Receipt(Cashier cashier, List<Product> items, double totalAmount) {
        this.cashier = cashier;
        this.items = items;
        this.totalAmount = totalAmount;
    }

    private long id;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> items;
    private double totalAmount;

    public long getId() {
        return id;
    }

    public List<Product> getItems() {
        return items;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}
