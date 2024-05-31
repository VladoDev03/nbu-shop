package org.example.repository.contracts;

import org.example.data.Product;
import org.example.data.Receipt;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    boolean addReceipt(Receipt receipt);
    List<Receipt> getAllReceipts();
}
