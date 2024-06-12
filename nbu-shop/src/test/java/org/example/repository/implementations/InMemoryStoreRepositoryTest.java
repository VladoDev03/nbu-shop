package org.example.repository.implementations;

import org.example.data.Receipt;
import org.example.repository.contracts.StoreRepository;
import org.example.repository.implementations.InMemoryStoreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryStoreRepositoryTest {
    private StoreRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryStoreRepository(); // Instantiate the repository
    }

    @Test
    void addReceipt_shouldAddReceiptToRepository() {
        // Arrange
        Receipt receipt = new Receipt();

        // Act
        boolean result = repository.addReceipt(receipt);

        // Assert
        assertTrue(result); // Adding receipt should return true
        assertTrue(repository.getAllReceipts().contains(receipt)); // Receipt should be added to the repository
    }

    @Test
    void getAllReceipts_shouldReturnListOfReceipts() {
        // Arrange
        Receipt receipt1 = new Receipt();
        Receipt receipt2 = new Receipt();
        repository.addReceipt(receipt1);
        repository.addReceipt(receipt2);

        // Act
        List<Receipt> result = repository.getAllReceipts();

        // Assert
        assertEquals(2, result.size()); // Should return list with correct number of receipts
        assertTrue(result.contains(receipt1)); // List should contain receipt1
        assertTrue(result.contains(receipt2)); // List should contain receipt2
    }
}
