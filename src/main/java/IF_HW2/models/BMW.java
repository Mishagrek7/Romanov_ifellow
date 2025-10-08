package IF_HW2.models;

import IF_HW2.parameters.Car;

public class BMW extends Car {
    public BMW(String model, int year, String transmission, String color, double engineCapacity, String fuelType, String drive) {
        super(model, year, transmission, color, "Германия", engineCapacity, fuelType, drive);
    }
}
