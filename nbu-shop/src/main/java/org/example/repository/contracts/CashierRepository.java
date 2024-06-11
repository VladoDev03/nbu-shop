package org.example.repository.contracts;

import org.example.data.Cashier;

import java.util.List;
import java.util.Optional;

public interface CashierRepository {
    boolean addCashier(Cashier cashier);
    Optional<Cashier> findCashierById(String id);
    List<Cashier> getAllCashiers();
}
