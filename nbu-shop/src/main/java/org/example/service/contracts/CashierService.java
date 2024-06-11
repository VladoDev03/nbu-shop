package org.example.service.contracts;

import org.example.data.Cashier;

import java.util.Optional;

public interface CashierService {
    boolean addCashier(Cashier cashier);
    Optional<Cashier> findCashierById(String id);
}
