package org.example.repository.implementations;

import org.example.data.Product;
import org.example.data.Receipt;
import org.example.repository.contracts.StoreRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryStoreRepository implements StoreRepository {
    private final List<Receipt> receipts;

    public InMemoryStoreRepository() {
        this.receipts = new ArrayList<>();
    }

    @Override
    public boolean addReceipt(Receipt receipt) {
        return this.receipts.add(receipt);
    }

    @Override
    public List<Receipt> getAllReceipts() {
        return new ArrayList<>(receipts);
    }
}
