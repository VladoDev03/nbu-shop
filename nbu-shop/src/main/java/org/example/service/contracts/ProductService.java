package org.example.service.contracts;

import org.example.data.Product;
import org.example.exceptions.InsufficientQuantityException;

public interface ProductService {
    boolean addProduct(Product product);
    Product findProductById(long id);
    boolean sellProduct(String productId, int quantity) throws InsufficientQuantityException;
}
