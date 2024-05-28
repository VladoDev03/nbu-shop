package org.example.service.implementations;

import org.example.data.Cashier;
import org.example.exceptions.InsufficientQuantityException;
import org.example.service.contracts.StoreService;

public class StoreServiceImpl implements StoreService {
    @Override
    public boolean sellProduct(Cashier cashier, String productId, int quantity) throws InsufficientQuantityException {
        return false;
    }

    @Override
    public int getTotalReceiptsCount() {
        return 0;
    }

    @Override
    public double getTotalRevenue() {
        return 0;
    }
}
