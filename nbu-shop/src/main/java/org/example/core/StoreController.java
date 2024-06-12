package org.example.core;

import org.example.data.Cashier;
import org.example.data.Product;
import org.example.enums.ProductCategory;
import org.example.exceptions.InsufficientQuantityException;
import org.example.service.contracts.CashierService;
import org.example.service.contracts.ProductService;
import org.example.service.contracts.StoreService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class StoreController {
    private final ProductService productService;
    private final CashierService cashierService;
    private final StoreService storeService;

    public StoreController(ProductService productService, CashierService cashierService, StoreService storeService) {
        this.productService = productService;
        this.cashierService = cashierService;
        this.storeService = storeService;
    }

    public void sellProduct(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter sell details: {cashier id} {product id} {quantity}");
                String sellInput = scanner.nextLine();
                String[] params = sellInput.split(" ");

                if (params.length != 3) {
                    throw new IllegalArgumentException("Invalid input format. Please enter ids and quantity separated by a space.");
                }

                String cId = params[0];
                String pId = params[1];
                int quantity = Integer.parseInt(params[2]);

                 Optional<Cashier> cashier = cashierService.findCashierById(cId);

                 if (cashier.isPresent()) {
                     storeService.sellProduct(cashier.get(), pId, quantity);
                     validInput = true;
                     System.out.println("Product sold successfully");
                 } else {
                     System.out.println("Cashier with ID " + cId + " not found.");
                 }

                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format. Please enter ids and quantity separated by a space.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (InsufficientQuantityException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void hireNewCashier(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter cashier details: {name} {monthly salary}");
                String cashierInput = scanner.nextLine();
                String[] params = cashierInput.split(" ");

                if (params.length != 2) {
                    throw new IllegalArgumentException("Invalid input format. Please enter name and monthly salary separated by a space.");
                }

                String name = params[0];
                double salary = Double.parseDouble(params[1]);

                Cashier cashier = new Cashier(name, salary);
                cashierService.addCashier(cashier);
                validInput = true;

                System.out.println("Cashier added successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid salary format. Please enter a valid number for the salary.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addNewProduct(Scanner scanner) {
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.println("Enter product details: {name} {purchase price} {category} {expiry date} {quantity}");
                System.out.println("Category must be either food(1) or no_food(2)");
                System.out.println("Expiry date must be {day}-{month}-{year}");

                String productInput = scanner.nextLine();
                String[] params = productInput.split(" ");

                if (params.length != 5) {
                    throw new IllegalArgumentException("Invalid input format. Please enter {name} {purchase price} {category} {expiry date} {quantity} separated by a space.");
                }

                String name = params[0];
                double purchasePrice = Double.parseDouble(params[1]);
                int categoryChoice = Integer.parseInt(params[2]);
                String expiryDateRaw = params[3];
                int quantity = Integer.parseInt(params[4]);

                ProductCategory category;

                switch (categoryChoice) {
                    case 1:
                        category = ProductCategory.FOOD;
                        break;
                    case 2:
                    default:
                        category = ProductCategory.NONFOOD;
                        break;
                }

                LocalDate expiryDate;

                if (category == ProductCategory.FOOD) {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    expiryDate = LocalDate.parse(expiryDateRaw, dateFormatter);
                } else {
                    expiryDate = LocalDate.MAX;
                }

                Product product = new Product(name, purchasePrice, category, expiryDate, quantity);
                productService.addProduct(product);
                validInput = true;

                System.out.println("Product added successfully.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a correct number format.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showProducts() {
        productService.getAllProducts().forEach(System.out::println);
    }

    public void showCashiers() {
        cashierService.getAllCashiers().forEach(System.out::println);
    }
}
