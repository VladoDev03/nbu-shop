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

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Welcome to NBU shop. Vladimir's Course Project for course CITB408 Програмиране на Java!");
        System.out.println("---------------------------------------------------------------------------------------");

//        Engine engine = new Engine();
//        engine.run();

        Cashier cashier = new Cashier("Joe", 12000);
        Product product = new Product("asd", "asd", 12, ProductCategory.FOOD, LocalDate.now(), 10);
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        Receipt receipt = new Receipt(cashier, products, 100, LocalDateTime.now());
        System.out.println(receipt.toString());

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
