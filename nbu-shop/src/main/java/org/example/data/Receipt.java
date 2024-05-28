package org.example.data;

import java.time.LocalDateTime;
import java.util.List;

public class Receipt {
    private int id;
    private Cashier cashier;
    private LocalDateTime dateTime;
    private List<Product> items;
    private double totalAmount;
}
