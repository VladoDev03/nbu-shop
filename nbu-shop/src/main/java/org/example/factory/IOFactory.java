package org.example.factory;

import org.example.enums.IOType;
import org.example.exceptions.InvalidIOType;
import org.example.io.console.ConsoleInput;
import org.example.io.console.ConsoleOutput;
import org.example.io.contracts.ProgramInput;
import org.example.io.contracts.ProgramOutput;
import org.example.io.csv.CsvInput;
import org.example.io.csv.CsvOutput;
import org.example.io.file.FileInput;
import org.example.io.file.FileOutput;
import org.example.io.json.JsonInput;
import org.example.io.json.JsonOutput;

public class IOFactory {
    private static IOFactory instance;

    private IOFactory() {}

    public static IOFactory getInstance() {
        if (instance == null) {
            synchronized (IOFactory.class) {
                if (instance == null) {
                    instance = new IOFactory();
                }
            }
        }
        return instance;
    }

    public ProgramInput CreateProgramInput(IOType type) throws InvalidIOType {
        switch (type) {
            case Console:
                return new ConsoleInput();
            case File:
                return new FileInput();
            case CSV:
                return new CsvInput();
            case JSON:
                return new JsonInput();
            default:
                throw new InvalidIOType();
        }
    }

    public ProgramOutput CreateProgramOutput(IOType type) throws InvalidIOType {
        switch (type) {
            case Console:
                return new ConsoleOutput();
            case File:
                return new FileOutput();
            case CSV:
                return new CsvOutput();
            case JSON:
                return new JsonOutput();
            default:
                throw new InvalidIOType();
        }
    }
}
