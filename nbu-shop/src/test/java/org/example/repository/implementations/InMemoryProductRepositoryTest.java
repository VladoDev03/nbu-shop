package org.example.repository.implementations;

import org.example.data.Product;
import org.example.repository.contracts.ProductRepository;
import org.example.repository.implementations.InMemoryProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryProductRepositoryTest {
    private ProductRepository repository;

    @BeforeEach
    void setUp() {
        repository = new InMemoryProductRepository(); // Instantiate the repository
    }

    @Test
    void addProduct_shouldAddProductToRepository() {
        // Arrange
        Product product = new Product(UUID.randomUUID().toString(), "Product A", 10.0, null, null, 10);

        // Act
        boolean result = repository.addProduct(product);

        // Assert
        assertTrue(result); // Adding product should return true
        assertTrue(repository.findProductById(product.getId()).isPresent()); // Product should be added to the repository
    }

    @Test
    void findProductById_shouldReturnProductIfExists() {
        // Arrange
        Product product = new Product(UUID.randomUUID().toString(), "Product A", 10.0, null, null, 10);
        repository.addProduct(product);

        // Act
        Optional<Product> result = repository.findProductById(product.getId());

        // Assert
        assertTrue(result.isPresent()); // Product should be found
        assertEquals(product, result.get()); // Found product should match the added product
    }

    @Test
    void findProductById_shouldReturnEmptyOptionalIfNotExists() {
        // Act
        Optional<Product> result = repository.findProductById("1");

        // Assert
        assertTrue(result.isEmpty()); // Should return empty optional if product does not exist
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        // Arrange
        Product product1 = new Product(UUID.randomUUID().toString(), "Product A", 10.0, null, null, 10);
        Product product2 = new Product(UUID.randomUUID().toString(), "Product B", 15.0, null, null, 15);
        repository.addProduct(product1);
        repository.addProduct(product2);

        // Act
        List<Product> result = repository.getAllProducts();

        // Assert
        assertEquals(2, result.size()); // Should return list with correct number of products
        assertTrue(result.contains(product1)); // List should contain product1
        assertTrue(result.contains(product2)); // List should contain product2
    }

    @Test
    void updateProduct_shouldReturnFalse() {
        // Arrange
        Product product = new Product(UUID.randomUUID().toString(), "Product A", 10.0, null, null, 10);

        // Act
        boolean result = repository.updateProduct(product);

        // Assert
        assertFalse(result); // Updating product should return false (not implemented in InMemoryProductRepository)
    }
}
