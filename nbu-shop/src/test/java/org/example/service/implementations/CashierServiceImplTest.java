package org.example.service.implementations;

import org.example.data.Cashier;
import org.example.repository.contracts.CashierRepository;
import org.example.service.implementations.CashierServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CashierServiceImplTest {
    @Mock
    private CashierRepository repo;

    private CashierServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        service = new CashierServiceImpl(repo); // Instantiate service with mocked repository
    }

    @Test
    void findCashierById_shouldReturnCashierWhenExists() {
        // Arrange
        String id = "1";
        Cashier existingCashier = new Cashier("1", "John Doe", 2000);
        when(repo.findCashierById(id)).thenReturn(Optional.of(existingCashier)); // Mock repository behavior

        // Act
        Optional<Cashier> result = service.findCashierById(id);

        // Assert
        assertTrue(result.isPresent()); // Should return optional containing cashier when it exists
        assertEquals(existingCashier, result.get()); // Returned cashier should match the existing cashier
    }

    @Test
    void findCashierById_shouldReturnEmptyOptionalWhenCashierDoesNotExist() {
        // Arrange
        String id = "1";
        when(repo.findCashierById(id)).thenReturn(Optional.empty()); // Mock repository behavior

        // Act
        Optional<Cashier> result = service.findCashierById(id);

        // Assert
        assertFalse(result.isPresent()); // Should return empty optional when cashier does not exist
    }

    @Test
    void getAllCashiers_shouldReturnListOfCashiers() {
        // Arrange
        List<Cashier> cashiers = new ArrayList<>();
        cashiers.add(new Cashier("1", "John Doe", 2000));
        cashiers.add(new Cashier("2", "Jane Smith", 2500));
        when(repo.getAllCashiers()).thenReturn(cashiers); // Mock repository behavior

        // Act
        List<Cashier> result = service.getAllCashiers();

        // Assert
        assertEquals(cashiers, result); // Returned list of cashiers should match the mocked list
    }
}
