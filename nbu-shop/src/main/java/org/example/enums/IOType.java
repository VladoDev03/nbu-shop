package org.example.enums;

public enum IOType {
    Console(1), File(2), CSV(3), JSON(4);

    private final int choice;

    IOType(int choice) {
        this.choice = choice;
    }

    public int getChoice() {
        return choice;
    }

    public static IOType fromChoice(int choice) {
        for (IOType type : IOType.values()) {
            if (type.getChoice() == choice) {
                return type;
            }
        }

        return Console;
    }
}
