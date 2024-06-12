package org.example.service.contracts;

import org.example.data.Product;
import org.example.exceptions.InsufficientQuantityException;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    boolean addProduct(Product product);
    Optional<Product> findProductById(String id);
    List<Product> getAllProducts();
}
