package org.example.service.contracts;

import org.example.data.Cashier;
import org.example.data.Receipt;
import org.example.exceptions.InsufficientQuantityException;

import java.util.List;

public interface StoreService {
    boolean sellProduct(Cashier cashier, String productId, int quantity) throws InsufficientQuantityException;
    int getTotalReceiptsCount();
    double getTotalRevenue();
    List<Receipt> getAllReceipts();
}
