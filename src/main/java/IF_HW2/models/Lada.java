package IF_HW2.models;

import IF_HW2.parameters.Car;

public class Lada extends Car {
    public Lada(String model, int year, String transmission, String color, double engineCapacity, String fuelType, String drive) {
        super(model, year, transmission, color, "Россия", engineCapacity, fuelType, drive);
    }
}

