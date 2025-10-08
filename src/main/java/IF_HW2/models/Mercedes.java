package IF_HW2.models;

import IF_HW2.parameters.Car;

public class Mercedes extends Car {
    public Mercedes(String model, int year, String transmission, String color, double engineCapacity, String fuelType, String drive) {
        super(model, year, transmission, color, "Германия", engineCapacity, fuelType, drive);
    }
}