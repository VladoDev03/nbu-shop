package org.example.core;

import org.example.enums.IOType;

import java.util.Scanner;

public class Engine {
    public void run() {
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

        IOType input = IOType.fromChoice(inputChoice);
        IOType output = IOType.fromChoice(outputChoice);

        System.out.println("You've selected the following input and output...");
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
    }
}
