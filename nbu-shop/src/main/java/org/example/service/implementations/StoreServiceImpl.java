package org.example.service.implementations;

import org.example.data.Cashier;
import org.example.data.Product;
import org.example.data.Receipt;
import org.example.exceptions.InsufficientQuantityException;
import org.example.repository.contracts.ProductRepository;
import org.example.repository.contracts.StoreRepository;
import org.example.service.contracts.ReceiptService;
import org.example.service.contracts.StoreService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class StoreServiceImpl implements StoreService {
    private final double foodMarkup;
    private final double nonFoodMarkup;
    private final int daysBeforeExpiryForDiscount;
    private final double discountPercentage;
    private final ProductRepository productRepo;
    private final StoreRepository storeRepo;
    private ReceiptService receiptService;

    public StoreServiceImpl(
            double foodMarkup,
            double nonFoodMarkup,
            int daysBeforeExpiryForDiscount,
            double discountPercentage,
            ProductRepository productRepo,
            StoreRepository storeRepo,
            ReceiptService receiptService
    ) {
        this.foodMarkup = foodMarkup;
        this.nonFoodMarkup = nonFoodMarkup;
        this.daysBeforeExpiryForDiscount = daysBeforeExpiryForDiscount;
        this.discountPercentage = discountPercentage;
        this.productRepo = productRepo;
        this.storeRepo = storeRepo;
        this.receiptService = receiptService;
    }

    @Override
    public boolean sellProduct(Cashier cashier, String productId, int quantity) throws InsufficientQuantityException {
        for (Product product : productRepo.getAllProducts()) {
            if (product.getId().equals(productId)) {
                if (product.getQuantity() < quantity) {
                    throw new InsufficientQuantityException(product.getName(), quantity - product.getQuantity());
                }

                if (product.getIsExpired()) {
                    throw new IllegalStateException("Product " + product.getName() + " is expired.");
                }

                List<Product> soldItems = new ArrayList<>();
                soldItems.add(new Product(product.getId(), product.getName(), product.getPurchasePrice(), product.getCategory(), product.getExpiryDate(), quantity));

                double totalAmount = soldItems.stream().mapToDouble(p -> p.getSellingPrice(foodMarkup, nonFoodMarkup, daysBeforeExpiryForDiscount, discountPercentage) * p.getQuantity()).sum();

                storeRepo.addReceipt(new Receipt(cashier, soldItems, totalAmount, LocalDateTime.now()));
                product.setQuantity(product.getQuantity() - quantity);

                Receipt receipt = new Receipt(cashier, soldItems, totalAmount, LocalDateTime.now());
                storeRepo.addReceipt(receipt);
                receiptService.saveToFile(receipt);

                return true;
            }
        }

        throw new NoSuchElementException("Product with ID " + productId + " not found.");
    }

    @Override
    public int getTotalReceiptsCount() {
        return storeRepo.getAllReceipts().size();
    }

    @Override
    public double getTotalRevenue() {
        return storeRepo.getAllReceipts().stream().mapToDouble(Receipt::getTotalAmount).sum();
    }
}
