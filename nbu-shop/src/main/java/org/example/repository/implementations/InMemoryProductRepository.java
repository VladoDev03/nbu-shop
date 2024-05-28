package org.example.repository.implementations;

import org.example.data.Cashier;
import org.example.data.Product;
import org.example.repository.contracts.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products;

    public InMemoryProductRepository() {
        this.products = new ArrayList<>();
    }

    @Override
    public boolean addProduct(Product product) {
        return products.add(product);
    }

    @Override
    public Optional<Product> findProductById(long id) {
        return products.stream().filter(p -> p.getId() == id).findFirst();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }
}
