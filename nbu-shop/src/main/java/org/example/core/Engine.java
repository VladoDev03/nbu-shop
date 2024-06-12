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
    private ReceiptService receiptService;

    public Engine(
            IOFactory ioFactory,
            CashierService cashierService,
            ProductService productService,
            StoreService storeService,
            ReceiptService receiptService
    ) {
        this.ioFactory = ioFactory;
        this.cashierService = cashierService;
        this.productService = productService;
        this.storeService = storeService;
        this.receiptService = receiptService;
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

        StoreController controller = new StoreController(productService, cashierService, storeService, receiptService);

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
            System.out.println("9. Receipts list");
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
                case 9:
                    controller.showReceipts();
                    break;
                default:
                    System.out.println("PLease choose a command between 1 and 8.");
                    break;
            }
        } while (isRunning);
    }
}
