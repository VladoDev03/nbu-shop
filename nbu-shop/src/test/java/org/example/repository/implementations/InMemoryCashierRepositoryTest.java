package org.example.repository.implementations;

import org.example.data.Cashier;
import org.example.repository.contracts.CashierRepository;
import org.example.repository.implementations.InMemoryCashierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryCashierRepositoryTest {
    private CashierRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryCashierRepository(); // Instantiate the repository
    }

    @Test
    void addCashier_shouldAddCashierToRepository() {
        // Arrange
        Cashier cashier = new Cashier("1", "John Doe", 2000.0);

        // Act
        boolean result = repository.addCashier(cashier);

        // Assert
        assertTrue(result); // Adding cashier should return true
        assertTrue(repository.findCashierById("1").isPresent()); // Cashier should be added to the repository
    }

    @Test
    void findCashierById_shouldReturnCashierIfExists() {
        // Arrange
        Cashier cashier = new Cashier("1", "John Doe", 2000.0);
        repository.addCashier(cashier);

        // Act
        Optional<Cashier> result = repository.findCashierById("1");

        // Assert
        assertTrue(result.isPresent()); // Cashier should be found
        assertEquals(cashier, result.get()); // Found cashier should match the added cashier
    }

    @Test
    void findCashierById_shouldReturnEmptyOptionalIfNotExists() {
        // Act
        Optional<Cashier> result = repository.findCashierById("1");

        // Assert
        assertTrue(result.isEmpty()); // Should return empty optional if cashier does not exist
    }

    @Test
    void getAllCashiers_shouldReturnListOfCashiers() {
        // Arrange
        Cashier cashier1 = new Cashier("1", "John Doe", 2000.0);
        Cashier cashier2 = new Cashier("2", "Jane Smith", 2500.0);
        repository.addCashier(cashier1);
        repository.addCashier(cashier2);

        // Act
        List<Cashier> result = repository.getAllCashiers();

        // Assert
        assertEquals(2, result.size()); // Should return list with correct number of cashiers
        assertTrue(result.contains(cashier1)); // List should contain cashier1
        assertTrue(result.contains(cashier2)); // List should contain cashier2
    }
}
