package IF_HW2.models;

import IF_HW2.parameters.Car;

public class Lexus extends Car {
    public Lexus(String model, int year, String transmission, String color, double engineCapacity, String fuelType, String drive) {
        super(model, year, transmission, color, "Япония", engineCapacity, fuelType, drive);
    }
}