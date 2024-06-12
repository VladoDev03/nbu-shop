package org.example.core;

import org.example.enums.IOType;
import org.example.exceptions.InvalidIOType;
import org.example.factory.IOFactory;
import org.example.io.contracts.ProgramInput;
import org.example.io.contracts.ProgramOutput;
import org.example.repository.contracts.CashierRepository;
import org.example.repository.contracts.ProductRepository;
import org.example.repository.contracts.StoreRepository;
import org.example.service.contracts.CashierService;
import org.example.service.contracts.ProductService;
import org.example.service.contracts.ReceiptService;
import org.example.service.contracts.StoreService;

import java.util.Scanner;

public class Engine {
    private IOFactory ioFactory;
    private CashierRepository cashierRepository;
    private ProductRepository productRepository;
    private StoreRepository storeRepository;
    private CashierService cashierService;
    private ProductService productService;
    private ReceiptService receiptService;
    private StoreService storeService;

    public Engine(
            IOFactory ioFactory,
            CashierRepository cashierRepository,
            ProductRepository productRepository,
            StoreRepository storeRepository,
            CashierService cashierService,
            ProductService productService,
            ReceiptService receiptService,
            StoreService storeService
    ) {
        this.ioFactory = ioFactory;
        this.cashierRepository = cashierRepository;
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
        this.cashierService = cashierService;
        this.productService = productService;
        this.receiptService = receiptService;
        this.storeService = storeService;
    }

    public void run() throws InvalidIOType {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1: Input through console");
        System.out.println("2: Input through file");
        System.out.println("3: Input through CSV");
        System.out.println("4: Input through JSON");
        System.out.print("Your choice: ");
        int inputChoice = scanner.nextInt();

        System.out.println("1: Output through console");
        System.out.println("2: Output through file");
        System.out.println("3: Output through CSV");
        System.out.println("4: Output through JSON");
        System.out.print("Your choice: ");
        int outputChoice = scanner.nextInt();

        IOType inputType = IOType.fromChoice(inputChoice);
        IOType outputType = IOType.fromChoice(outputChoice);

        System.out.println("You've selected the following input and output...");
        System.out.println("Input: " + inputType);
        System.out.println("Output: " + outputType);

        ProgramInput input = ioFactory.CreateProgramInput(inputType);
        ProgramOutput output = ioFactory.CreateProgramOutput(outputType);

        int commandChoice = 0;
        boolean isRunning = true;

        do {
            System.out.println("-------Commands-------");
            System.out.println("1. Sell product by id");
            System.out.println("2. Add new product");
            System.out.println("3. Employ new cashier");
            System.out.println("4. See total salaries");
            System.out.println("5. See earnings");
            System.out.println("6. Receipts count");
            System.out.println("0. End program");
            commandChoice = scanner.nextInt();

            switch (commandChoice) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    System.out.println("Sell");
                    break;
                case 2:
                    System.out.println("New Product");
                    break;
                case 3:
                    System.out.println("New Cashier");
                    break;
                case 4:
                    System.out.println("Salaries");
                    break;
                case 5:
                    System.out.println("Earnings");
                    break;
                case 6:
                    System.out.println("Receipts");
                    break;
                default:
                    System.out.println("PLease choose a command between 1 and 3.");
                    break;
            }
        } while (isRunning);
    }
}
