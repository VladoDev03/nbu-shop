package org.example.service.contracts;

import org.example.data.Receipt;

public interface ReceiptService {
    boolean saveToFile(Receipt receipts);
}
