package org.example.data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Receipt {
    private String id;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> items;
    private double totalAmount;

    public Receipt() {
        items = new ArrayList<>();
    }

    public Receipt(Cashier cashier, List<Product> items, double totalAmount, LocalDateTime dateTime) {
        this.id = UUID.randomUUID().toString();
        this.cashier = cashier;
        this.items = items;
        this.totalAmount = totalAmount;
        this.dateTime = dateTime;
    }

    public String getId() {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id).append(System.lineSeparator());
        sb.append(cashier.getName()).append(System.lineSeparator());

        sb
                .append("at ")
                .append(dateTime.getHour())
                .append(":")
                .append(dateTime.getMinute())
                .append(System.lineSeparator())
                .append("on ")
                .append(dateTime.getDayOfMonth())
                .append(" ")
                .append(dateTime.getMonth())
                .append(System.lineSeparator());

        items.forEach(i -> sb.append(i.getName()).append(" -> ").append(i.getQuantity()));
        sb.append(System.lineSeparator());
        sb.append(totalAmount).append(System.lineSeparator());

        return sb.toString();
    }
}
