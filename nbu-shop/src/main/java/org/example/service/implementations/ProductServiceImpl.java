package org.example.service.implementations;

import org.example.data.Product;
import org.example.exceptions.InsufficientQuantityException;
import org.example.service.contracts.ProductService;

public class ProductServiceImpl implements ProductService {
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public Product findProductById(String id) {
        return null;
    }

    @Override
    public boolean sellProduct(String productId, int quantity) throws InsufficientQuantityException {
        return false;
    }
}
