package org.example.core;

import org.example.data.Cashier;
import org.example.data.Product;
import org.example.enums.IOType;
import org.example.enums.ProductCategory;
import org.example.exceptions.InsufficientQuantityException;
import org.example.exceptions.InvalidIOType;
import org.example.factory.IOFactory;
import org.example.io.contracts.ProgramInput;
import org.example.io.contracts.ProgramOutput;
import org.example.service.contracts.CashierService;
import org.example.service.contracts.ProductService;
import org.example.service.contracts.ReceiptService;
import org.example.service.contracts.StoreService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class Engine {
    private IOFactory ioFactory;
    private CashierService cashierService;
    private ProductService productService;
    private StoreService storeService;

    public Engine(
            IOFactory ioFactory,
            CashierService cashierService,
            ProductService productService,
            StoreService storeService
    ) {
        this.ioFactory = ioFactory;
        this.cashierService = cashierService;
        this.productService = productService;
        this.storeService = storeService;
    }

    public void run() throws InvalidIOType {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1: Input through console");
        System.out.println("2: Input through file");
        System.out.println("3: Input through CSV");
        System.out.println("4: Input through JSON");
        System.out.print("Your choice: ");
        int inputChoice = Integer.parseInt(scanner.nextLine());

        System.out.println("1: Output through console");
        System.out.println("2: Output through file");
        System.out.println("3: Output through CSV");
        System.out.println("4: Output through JSON");
        System.out.print("Your choice: ");
        int outputChoice = Integer.parseInt(scanner.nextLine());

        IOType inputType = IOType.fromChoice(inputChoice);
        IOType outputType = IOType.fromChoice(outputChoice);

        System.out.println("You've selected the following input and output...");
        System.out.println("Input: " + inputType);
        System.out.println("Output: " + outputType);

        StoreController controller = new StoreController(productService, cashierService, storeService);

        ProgramInput input = ioFactory.CreateProgramInput(inputType);
        ProgramOutput output = ioFactory.CreateProgramOutput(outputType);

        int commandChoice = 0;
        boolean isRunning = true;

        do {
            System.out.println("-------Commands-------");
            System.out.println("1. Sell product by id");
            System.out.println("2. Add new product");
            System.out.println("3. Hire new cashier");
            System.out.println("4. See total salaries");
            System.out.println("5. See revenue");
            System.out.println("6. Receipts count");
            System.out.println("7. Products list");
            System.out.println("8. Cashiers list");
            System.out.println("0. End program");
            commandChoice = Integer.parseInt(scanner.nextLine());

            switch (commandChoice) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    controller.sellProduct(scanner);
                    break;
                case 2:
                    controller.addNewProduct(scanner);
                    break;
                case 3:
                    controller.hireNewCashier(scanner);
                    break;
                case 4:
                    System.out.println("The total cashier salary is: " + cashierService.getTotalCashierSalary() + "$");
                    break;
                case 5:
                    System.out.println("The revenue of the store is: " + storeService.getTotalRevenue() + "$");
                    break;
                case 6:
                    System.out.println("Total amout of receipts is: " + storeService.getTotalReceiptsCount());
                    break;
                case 7:
                    controller.showProducts();
                    break;
                case 8:
                    controller.showCashiers();
                    break;
                default:
                    System.out.println("PLease choose a command between 1 and 8.");
                    break;
            }
        } while (isRunning);
    }

//    private void sellProduct(Scanner scanner) {
//        boolean validInput = false;
//
//        while (!validInput) {
//            try {
//                System.out.println("Enter sell details: {cashier id} {product id} {quantity}");
//                String sellInput = scanner.nextLine();
//                String[] params = sellInput.split(" ");
//
//                if (params.length != 3) {
//                    throw new IllegalArgumentException("Invalid input format. Please enter ids and quantity separated by a space.");
//                }
//
//                String cId = params[0];
//                String pId = params[1];
//                int quantity = Integer.parseInt(params[2]);
//
//                Optional<Cashier> cashier = cashierService.findCashierById(cId);
//
//                storeService.sellProduct(cashier.get(), pId, quantity);
//
//                validInput = true;
//
//                System.out.println("Product sold successfully");
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input format. Please enter ids and quantity separated by a space.");
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            } catch (InsufficientQuantityException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    private void hireNewCashier(Scanner scanner) {
//        boolean validInput = false;
//
//        while (!validInput) {
//            try {
//                System.out.println("Enter cashier details: {name} {monthly salary}");
//                String cashierInput = scanner.nextLine();
//                String[] params = cashierInput.split(" ");
//
//                if (params.length != 2) {
//                    throw new IllegalArgumentException("Invalid input format. Please enter name and monthly salary separated by a space.");
//                }
//
//                String name = params[0];
//                double salary = Double.parseDouble(params[1]);
//
//                Cashier cashier = new Cashier(name, salary);
//                cashierService.addCashier(cashier);
//                validInput = true;
//
//                System.out.println("Cashier added successfully.");
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid salary format. Please enter a valid number for the salary.");
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    private void addNewProduct(Scanner scanner) {
//        boolean validInput = false;
//
//        while (!validInput) {
//            try {
//                System.out.println("Enter product details: {name} {purchase price} {category} {expiry date} {quantity}");
//                System.out.println("Category must be either food(1) or no_food(2)");
//                System.out.println("Expiry date must be {day}-{month}-{year}");
//
//                String productInput = scanner.nextLine();
//                String[] params = productInput.split(" ");
//
//                if (params.length != 5) {
//                    throw new IllegalArgumentException("Invalid input format. Please enter {name} {purchase price} {category} {expiry date} {quantity} separated by a space.");
//                }
//
//                String name = params[0];
//                double purchasePrice = Double.parseDouble(params[1]);
//                int categoryChoice = Integer.parseInt(params[2]);
//                String expiryDateRaw = params[3];
//                int quantity = Integer.parseInt(params[4]);
//
//                ProductCategory category;
//
//                switch (categoryChoice) {
//                    case 1:
//                        category = ProductCategory.FOOD;
//                        break;
//                    case 2:
//                    default:
//                        category = ProductCategory.NONFOOD;
//                        break;
//                }
//
//                LocalDate expiryDate;
//
//                if (category == ProductCategory.FOOD) {
//                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//                    expiryDate = LocalDate.parse(expiryDateRaw, dateFormatter);
//                } else {
//                    expiryDate = LocalDate.MAX;
//                }
//
//                Product product = new Product(name, purchasePrice, category, expiryDate, quantity);
//                productService.addProduct(product);
//                validInput = true;
//
//                System.out.println("Product added successfully.");
//            } catch (NumberFormatException e) {
//                System.out.println("Please enter a correct number format.");
//            } catch (IllegalArgumentException e) {
//                System.out.println(e.getMessage());
//            }
//        }
//    }
//
//    private void showProducts() {
//        productService.getAllProducts().forEach(System.out::println);
//    }
//
//    private void showCashiers() {
//        cashierService.getAllCashiers().forEach(System.out::println);
//    }
}
