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
        StoreService storeService = new StoreServiceImpl(1, 1, 10, 3, productRepository, storeRepository, receiptService);

        // Initializing program engine
        Engine engine = new Engine(
                ioFactory,
                cashierService,
                productService,
                receiptService,
                storeService);

        engine.run();

//        Cashier cashier = new Cashier("Joe", 12000);
//        Product product = new Product("asd", "asd", 12, ProductCategory.FOOD, LocalDate.now(), 10);
//        ArrayList<Product> products = new ArrayList<>();
//        products.add(product);
//        Receipt receipt = new Receipt(cashier, products, 100, LocalDateTime.now());
//        System.out.println(receipt.toString());
//
//        ProductRepository productRepo = new InMemoryProductRepository();
//        productRepo.addProduct(product);
//        StoreRepository storeRepo = new InMemoryStoreRepository();
//        ReceiptService receiptService = new ReceiptServiceImpl();
//        StoreService serv = new StoreServiceImpl(1, 1, 10, 3, productRepo, storeRepo, receiptService);
//        serv.sellProduct(cashier, product.getId(), 2);
//        System.out.println(product.getQuantity());

//         Write Json
//        Cashier cashier = new Cashier("Joe", 12000);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(new File("files/cashier.json"), cashier);
//        String jsonString = mapper.writeValueAsString(cashier);
//
//        Cashier cashier = new Cashier("Joe", 12000);

        // Write CSV
//        try (Writer writer = new FileWriter("files/cashier.csv")) {
//            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
//            printer.printRecord("Id", "Name", "Monthly Salary");
//            printer.printRecord(Arrays.asList(cashier.getId(), cashier.getName(), cashier.getMonthlySalary()));
//        }

//         Read CSV
//        try (FileReader fis = new FileReader("files/cashier.csv")) {
//            BufferedReader bufferedReader = new BufferedReader(fis);
//            String line;
//
//            List<Cashier> cashiers = new ArrayList<>();
//
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] props = line.split(",");
//
//                double salary = 0;
//
//                try {
//                    salary = Double.parseDouble(props[2]);
//                } catch (NumberFormatException ex) {
//                    continue;
//                }
//
//                Cashier cashier = new Cashier(props[0], props[1], salary);
//                cashiers.add(cashier);
//            }
//
//            cashiers.forEach(c -> System.out.println(c.getName()));
//        }

//         Read Json
//        ObjectMapper mapper = new ObjectMapper();
//        Cashier cashier = new Cashier("Joe", 12000);
//        String jsonString = mapper.writeValueAsString(cashier);
//        JsonNode node = mapper.readTree(jsonString);
//        System.out.println(node.get("name").asText());
    }
}
