package org.example.repository.implementations;

import org.example.data.Cashier;
import org.example.repository.contracts.CashierRepository;

import java.util.List;

public class InMemoryCashierRepository implements CashierRepository {
    @Override
    public boolean addCashier(Cashier cashier) {
        return false;
    }

    @Override
    public Cashier findCashierById(String id) {
        return null;
    }

    @Override
    public List<Cashier> getAllCashiers() {
        return null;
    }
}
