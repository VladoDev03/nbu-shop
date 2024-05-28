package org.example.repository.contracts;

import org.example.data.Product;

import java.util.List;

public interface ProductRepository {
    boolean addProduct(Product product);
    Product findProductById(String id);
    List<Product> getAllProducts();
    boolean updateProduct(Product product);
}
