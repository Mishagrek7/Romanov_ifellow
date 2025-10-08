package IF_HW2.parameters;

import java.util.List;

public class CarUtils {

    public static void printRecentCars(List<Car> cars) {
        for (Car car : cars) {
            if (car.getYear() > 2006) {
                System.out.println(car.getInfo());
            } else {
                System.out.println(car.getModel() + " - устаревший авто");
            }
        }
    }

    public static void changeGreenToRed(List<Car> cars) {
        for (Car car : cars) {
            if ("зеленый".equalsIgnoreCase(car.getColor().trim())) {
                car.changeColor("красный");
            }
        }
    }

    public static void printCarsByFuelType(List<Car> cars, String fuelType) {
        for (Car car : cars) {
            if (car.getFuelType().equalsIgnoreCase(fuelType)) {
                System.out.println(car.getInfo());
            }
        }
    }

    public static void printCarsByDrivee(List<Car> cars, String drive) {
        for (Car car : cars) {
            if (car.getDrive().equalsIgnoreCase(drive)) {
                System.out.println(car.getInfo());
            }
        }
    }

    public static void printCarsByTransmission(List<Car> cars, String transmission) {
        for (Car car : cars) {
            if (car.getTransmission().equalsIgnoreCase(transmission)) {
                System.out.println(car.getInfo());
            }
        }
    }

    public static void printCarsByManufacturer(List<Car> cars, String manufacturer) {
        for (Car car : cars) {
            if (car.getManufacturer().equalsIgnoreCase(manufacturer)) {
                System.out.println(car.getInfo());
            }
        }
    }
}
