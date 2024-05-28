package org.example.repository.contracts;

import org.example.data.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    boolean addProduct(Product product);
    Optional<Product> findProductById(long id);
    List<Product> getAllProducts();
    boolean updateProduct(Product product);
}
