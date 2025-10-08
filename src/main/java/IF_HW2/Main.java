package IF_HW2;

import IF_HW2.models.*;
import IF_HW2.parameters.Car;
import IF_HW2.parameters.CarUtils;
import IF_HW2.parameters.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
            List<Car> cars = new ArrayList<>();

            cars.add(new Toyota("Camry", 2013, "механика", "черный", 2.4,"бензин", "передний"));
            cars.add(new Lexus("LX", 2005, "автомат", "белый", 4.5,"бензин", "полный"));
            cars.add(new Mercedes("GLE", 2005, "автомат", "зеленый", 3.0,"дизель", "полный"));
            cars.add(new Porsche("Panamera", 2015, "автомат", "серый", 4.5,"бензин", "полный"));
            cars.add(new Lada("Granta", 2012, "механика", "зеленый", 1.8,"бензин", "передний"));
            cars.add(new Toyota("Corolla", 2007, "механика", "синий", 1.6,"бензин", "передний"));
            cars.add(new Lexus("RX", 2019, "автомат", "зеленый", 1.8,"бензин", "полный"));
            cars.add(new Mercedes("GLS", 2004, "автомат", "белый", 5.5,"бензин", "полный"));
            cars.add(new Porsche("Cayenne", 2020, "автомат", "черный", 4.0,"бензин", "полный"));
            cars.add(new Lada("Vesta", 2015, "механика", "зеленый", 1.8,"бензин", "передний"));
            cars.add(new BMW("E39", 2000, "автомат", "серый", 3.0,"бензин", "задний"));


            System.out.println("Автомобили, выпущенные после 2006 года:");
            CarUtils.printRecentCars(cars);

            System.out.println("\nПосле изменения цвета зеленых автомобилей на красный:");
            CarUtils.changeGreenToRed(cars);
            cars.forEach(car -> System.out.println(car.getInfo()));

            System.out.println("\nАвтомобили с механической коробкой:");
            CarUtils.printCarsByTransmission(cars, "механика");

            System.out.println("\nАвтомобили с автоматической коробкой:");
            CarUtils.printCarsByTransmission(cars, "автомат");

            System.out.println("\nАвтомобили с бензиновым топливом:");
            CarUtils.printCarsByFuelType(cars, "бензин");

            System.out.println("\nАвтомобили с дизельным топливом:");
            CarUtils.printCarsByFuelType(cars, "дизель");

            System.out.println("\nАвтомобили с передним приводом:");
            CarUtils.printCarsByDrivee(cars, "передний");

            System.out.println("\nАвтомобили с задним приводом:");
            CarUtils.printCarsByDrivee(cars, "задний");

            System.out.println("\nАвтомобили с полным приводом:");
            CarUtils.printCarsByDrivee(cars, "полный");

            System.out.println("\nАвтомобили из Японии:");
            CarUtils.printCarsByManufacturer(cars, "Япония");



        }
    }


