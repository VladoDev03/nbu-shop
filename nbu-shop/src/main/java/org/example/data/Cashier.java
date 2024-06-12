package org.example.data;

import java.util.UUID;

public class Cashier {
    private String id;
    private String name;
    private double monthlySalary;

    public Cashier(String name, double monthlySalary) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public Cashier(String id, String name, double monthlySalary) {
        this.id = id;
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMonthlySalary() {
        return monthlySalary;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Id: ").append(getId()).append(System.lineSeparator());
        sb.append("Name: ").append(getName()).append(System.lineSeparator());
        sb.append("Monthly salary: ").append(getMonthlySalary()).append(System.lineSeparator());

        return sb.toString();
    }
}
