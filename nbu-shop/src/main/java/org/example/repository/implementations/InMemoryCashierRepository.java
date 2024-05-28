package org.example.repository.implementations;

import org.example.data.Cashier;
import org.example.repository.contracts.CashierRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryCashierRepository implements CashierRepository {
    private final List<Cashier> cashiers;

    public InMemoryCashierRepository() {
        this.cashiers = new ArrayList<>();
    }

    @Override
    public boolean addCashier(Cashier cashier) {
        return cashiers.add(cashier);
    }

    @Override
    public Optional<Cashier> findCashierById(long id) {
        return cashiers.stream().filter(c -> c.getId() == id).findFirst();
    }

    @Override
    public List<Cashier> getAllCashiers() {
        return new ArrayList<>(cashiers);
    }
}
