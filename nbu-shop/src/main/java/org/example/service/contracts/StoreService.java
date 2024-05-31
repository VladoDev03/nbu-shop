package org.example.service.contracts;

import org.example.data.Cashier;
import org.example.exceptions.InsufficientQuantityException;

public interface StoreService {
    boolean sellProduct(Cashier cashier, long productId, int quantity) throws InsufficientQuantityException;
    int getTotalReceiptsCount();
    double getTotalRevenue();
}
