package org.example.service.implementations;

import org.example.data.Product;
import org.example.data.Receipt;
import org.example.service.contracts.ReceiptService;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReceiptServiceImpl implements ReceiptService {
    @Override
    public boolean saveToFile(Receipt receipt) {
        try (PrintWriter out = new PrintWriter(new FileWriter("receipt_" + receipt.getId() + ".txt"))) {
            System.out.println("Receipt ID: " + receipt.getId());
            System.out.println("Cashier: " + receipt.getCashier().getName());
            System.out.println("Date and Time: " + receipt.getDateTime());
            System.out.println("Items:");

            for (Product item : receipt.getItems()) {
                System.out.println(" - " + item.getName() + " x " + item.getQuantity() + " @ " + item.getPurchasePrice() + " each");
            }

            System.out.println("Total Amount: " + receipt.getTotalAmount());

            return true;
        } catch (IOException e) {
            e.printStackTrace();

            return false;
        }
    }
}
