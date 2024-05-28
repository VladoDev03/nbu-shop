package org.example.repository.implementations;

import org.example.data.Product;
import org.example.repository.contracts.ProductRepository;

import java.util.List;

public class InMemoryProductRepository implements ProductRepository {
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public Product findProductById(String id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }
}
