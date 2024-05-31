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
            out.println("Receipt ID: " + receipt.getId());
            out.println("Cashier: " + receipt.getCashier().getName());
            out.println("Date and Time: " + receipt.getDateTime());
            out.println("Items:");
            for (Product item : receipt.getItems()) {
                out.println(" - " + item.getName() + " x " + item.getQuantity() + " @ " + item.getPurchasePrice() + " each");
            }
            out.println("Total Amount: " + receipt.getTotalAmount());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
