package org.example.service.contracts;

import org.example.data.Cashier;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CashierService {
    boolean addCashier(Cashier cashier);
    Optional<Cashier> findCashierById(String id);
    List<Cashier> getAllCashiers();
    BigDecimal getTotalCashierSalary();
}
