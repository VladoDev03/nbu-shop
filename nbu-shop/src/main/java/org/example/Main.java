package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.example.core.Engine;
import org.example.data.Cashier;
import org.example.data.Product;
import org.example.data.Receipt;
import org.example.enums.ProductCategory;
import org.example.exceptions.InsufficientQuantityException;
import org.example.exceptions.InvalidIOType;
import org.example.factory.IOFactory;
import org.example.repository.contracts.CashierRepository;
import org.example.repository.contracts.ProductRepository;
import org.example.repository.contracts.StoreRepository;
import org.example.repository.implementations.InMemoryCashierRepository;
import org.example.repository.implementations.InMemoryProductRepository;
import org.example.repository.implementations.InMemoryStoreRepository;
import org.example.service.contracts.CashierService;
import org.example.service.contracts.ProductService;
import org.example.service.contracts.ReceiptService;
import org.example.service.contracts.StoreService;
import org.example.service.implementations.CashierServiceImpl;
import org.example.service.implementations.ProductServiceImpl;
import org.example.service.implementations.ReceiptServiceImpl;
import org.example.service.implementations.StoreServiceImpl;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InsufficientQuantityException, InvalidIOType {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Welcome to NBU shop. Vladimir's Course Project for course CITB408 Програмиране на Java!");
        System.out.println("---------------------------------------------------------------------------------------");

        // Initializing singletons
        IOFactory ioFactory = IOFactory.getInstance();

        // Initializing repositories
        CashierRepository cashierRepository = new InMemoryCashierRepository();
        ProductRepository productRepository = new InMemoryProductRepository();
        StoreRepository storeRepository = new InMemoryStoreRepository();

        // Initializing services
        CashierService cashierService = new CashierServiceImpl(cashierRepository);
        ProductService productService = new ProductServiceImpl(productRepository);
        ReceiptService receiptService = new ReceiptServiceImpl();
        StoreService storeService = new StoreServiceImpl(5, 5, 10, 3, productRepository, storeRepository, receiptService);

        // Initializing program engine
        Engine engine = new Engine(
                ioFactory,
                cashierService,
                productService,
                storeService);

        engine.run();
    }
}
