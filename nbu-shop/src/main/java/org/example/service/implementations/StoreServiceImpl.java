package org.example.service.implementations;

import org.example.data.Cashier;
import org.example.data.Product;
import org.example.data.Receipt;
import org.example.exceptions.InsufficientQuantityException;
import org.example.repository.contracts.ProductRepository;
import org.example.repository.contracts.StoreRepository;
import org.example.service.contracts.StoreService;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreServiceImpl implements StoreService {
    private List<Product> products;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;
    private double foodMarkup;
    private double nonFoodMarkup;
    private int daysBeforeExpiryForDiscount;
    private double discountPercentage;
    private ProductRepository productRepo;

    public StoreServiceImpl(double foodMarkup, double nonFoodMarkup, int daysBeforeExpiryForDiscount, double discountPercentage, ProductRepository productRepo) {
        this.products = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.foodMarkup = foodMarkup;
        this.nonFoodMarkup = nonFoodMarkup;
        this.daysBeforeExpiryForDiscount = daysBeforeExpiryForDiscount;
        this.discountPercentage = discountPercentage;
        this.productRepo = productRepo;
    }

    @Override
    public boolean sellProduct(Cashier cashier, long productId, int quantity) throws InsufficientQuantityException {
        for (Product product : productRepo.getAllProducts()) {
            if (product.getId() == productId) {
                if (product.getQuantity() < quantity) {
                    throw new InsufficientQuantityException(product.getName(), quantity - product.getQuantity());
                }
                if (product.getIsExpired()) {
                    throw new IllegalStateException("Product " + product.getName() + " is expired.");
                }
                List<Product> soldItems = new ArrayList<>();
                soldItems.add(new Product(product.getId(), product.getName(), product.getPurchasePrice(), product.getCategory(), product.getExpiryDate(), quantity));
                double totalAmount = soldItems.stream().mapToDouble(p -> p.getSellingPrice(foodMarkup, nonFoodMarkup, daysBeforeExpiryForDiscount, discountPercentage) * p.getQuantity()).sum();
                receipts.add(new Receipt(cashier, soldItems, totalAmount));
                product.setQuantity(product.getQuantity() - quantity);
                return true;
            }
        }
        throw new NoSuchElementException("Product with ID " + productId + " not found.");
    }

    @Override
    public int getTotalReceiptsCount() {
        return receipts.size();
    }

    @Override
    public double getTotalRevenue() {
        return receipts.stream().mapToDouble(Receipt::getTotalAmount).sum();
    }
}
