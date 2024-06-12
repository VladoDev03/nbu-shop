package org.example.service.implementations;

import org.example.data.Product;
import org.example.repository.contracts.ProductRepository;
import org.example.service.implementations.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {
    @Mock
    private ProductRepository repo;

    private ProductServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // Initialize mocks

        service = new ProductServiceImpl(repo); // Instantiate service with mocked repository
    }

    @Test
    void addProduct_shouldReturnFalseWhenProductExists() {
        // Arrange
        String existingProductId = UUID.randomUUID().toString();
        Product existingProduct = new Product(existingProductId, "Product A", 10.0, null, LocalDate.now(), 10);
        when(repo.findProductById(existingProductId)).thenReturn(Optional.of(existingProduct)); // Mock repository behavior

        // Act
        boolean result = service.addProduct(existingProduct);

        // Assert
        assertFalse(result); // Should return false when product already exists
        verify(repo, never()).addProduct(existingProduct); // Verify that addProduct method of repository was not called
    }

    @Test
    void findProductById_shouldReturnProductWhenExists() {
        // Arrange
        String productId = UUID.randomUUID().toString();
        Product existingProduct = new Product(productId, "Product A", 10.0, null, LocalDate.now(), 10);
        when(repo.findProductById(productId)).thenReturn(Optional.of(existingProduct)); // Mock repository behavior

        // Act
        Optional<Product> result = service.findProductById(productId);

        // Assert
        assertTrue(result.isPresent()); // Should return optional containing product when it exists
        assertEquals(existingProduct, result.get()); // Returned product should match the existing product
    }

    @Test
    void findProductById_shouldReturnEmptyOptionalWhenProductDoesNotExist() {
        // Arrange
        String productId = UUID.randomUUID().toString();
        when(repo.findProductById(productId)).thenReturn(Optional.empty()); // Mock repository behavior

        // Act
        Optional<Product> result = service.findProductById(productId);

        // Assert
        assertFalse(result.isPresent()); // Should return empty optional when product does not exist
    }

    @Test
    void getAllProducts_shouldReturnListOfProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product(UUID.randomUUID().toString(), "Product A", 10.0, null, LocalDate.now(), 10));
        products.add(new Product(UUID.randomUUID().toString(), "Product B", 15.0, null, LocalDate.now(), 15));
        when(repo.getAllProducts()).thenReturn(products); // Mock repository behavior

        // Act
        List<Product> result = service.getAllProducts();

        // Assert
        assertEquals(products, result); // Returned list of products should match the mocked list
    }
}
