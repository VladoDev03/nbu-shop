package org.example.service.contracts;

import org.example.data.Cashier;

public interface CashierService {
    boolean addCashier(Cashier cashier);
    Cashier findCashierById(long id);
}
