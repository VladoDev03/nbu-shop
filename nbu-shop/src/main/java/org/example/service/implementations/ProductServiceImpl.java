package org.example.service.implementations;

import org.example.data.Product;
import org.example.exceptions.InsufficientQuantityException;
import org.example.repository.contracts.ProductRepository;
import org.example.service.contracts.ProductService;

import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository repo;

    public ProductServiceImpl(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean notExisting = repo.findProductById(product.getId()).isEmpty();

        if (notExisting) {
            return repo.addProduct(product);
        }

        return false;
    }

    @Override
    public Optional<Product> findProductById(String id) {
        return repo.findProductById(id);
    }

    @Override
    public boolean sellProduct(String productId, int quantity) throws InsufficientQuantityException {
        return false;
    }
}
