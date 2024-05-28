package org.example.data;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt {
    private long id;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> items;
    private double totalAmount;

    public long getId() {
        return id;
    }
}
