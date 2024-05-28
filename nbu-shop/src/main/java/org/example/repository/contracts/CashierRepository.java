package org.example.repository.contracts;

import org.example.data.Cashier;

import java.util.List;

public interface CashierRepository {
    boolean addCashier(Cashier cashier);
    Cashier findCashierById(String id);
    List<Cashier> getAllCashiers();
}
