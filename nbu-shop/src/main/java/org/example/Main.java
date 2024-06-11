package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.example.core.Engine;
import org.example.data.Cashier;
import org.example.enums.IOType;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Welcome to NBU shop. Vladimir's Course Project for course CITB408 Програмиране на Java!");
        System.out.println("---------------------------------------------------------------------------------------");

//        Engine engine = new Engine();
//        engine.run();

        // Write Json
//        Cashier cashier = new Cashier("Joe", 12000);
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(new File("files/cashier.json"), cashier);
//        String jsonString = mapper.writeValueAsString(cashier);

//        Cashier cashier = new Cashier("Joe", 12000);
//
//        // Write CSV
//        try (Writer writer = new FileWriter("files/cashier.csv")) {
//            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT);
//            printer.printRecord("Id", "Name", "Monthly Salary");
//            printer.printRecord(Arrays.asList(cashier.getId(), cashier.getName(), cashier.getMonthlySalary()));
//        }

        // Read CSV
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

        // Read Json
//        ObjectMapper mapper = new ObjectMapper();
//        Cashier cashier = new Cashier("Joe", 12000);
//        String jsonString = mapper.writeValueAsString(cashier);
//        JsonNode node = mapper.readTree(jsonString);
//        System.out.println(node.get("name").asText());
    }
}
