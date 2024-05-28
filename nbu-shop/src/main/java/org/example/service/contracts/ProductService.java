package org.example.service.contracts;

import org.example.data.Product;
import org.example.exceptions.InsufficientQuantityException;

import java.util.Optional;

public interface ProductService {
    boolean addProduct(Product product);
    Optional<Product> findProductById(long id);
    boolean sellProduct(String productId, int quantity) throws InsufficientQuantityException;
}
