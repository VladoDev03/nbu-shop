package org.example.service.implementations;

import org.example.data.Cashier;
import org.example.repository.contracts.CashierRepository;
import org.example.service.contracts.CashierService;

import java.util.Optional;

public class CashierServiceImpl implements CashierService {
    private CashierRepository repo;

    public CashierServiceImpl(CashierRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addCashier(Cashier cashier) {
        boolean notExisting = repo.findCashierById(cashier.getId()).isEmpty();

        if (notExisting) {
            return repo.addCashier(cashier);
        }

        return false;
    }

    @Override
    public Optional<Cashier> findCashierById(String id) {
        return repo.findCashierById(id);
    }
}
