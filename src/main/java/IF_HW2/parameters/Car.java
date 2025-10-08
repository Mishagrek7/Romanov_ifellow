package IF_HW2.parameters;

public abstract class Car {
    private String model;
    private int year;
    private String transmission;
    private String color;
    private String manufacturer;
    private double engineCapacity;
    private String fuelType;
    private String drive;

    public Car(String model, int year, String transmission, String color, String manufacturer, double engineCapacity, String fuelType, String drive) {
        this.model = model;
        this.year = year;
        this.transmission = transmission;
        this.color = color;
        this.manufacturer = manufacturer;
        this.engineCapacity = engineCapacity;
        this.fuelType = fuelType;
        this.drive = drive;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getColor() {
        return color;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public String getFuelType() {
        return fuelType;
    }

    public String getDrive() {
        return drive;
    }

    public void changeColor(String newColor) {
        this.color = newColor;
    }

    public String getInfo() {
        return String.format("Model: %s, Year: %d, Transmission: %s, Color: %s, Manufacturer: %s, Engine Capacity: %.1fL, Fuel Type: %s, Drive: %s",
                model, year, transmission, color, manufacturer, engineCapacity, fuelType, drive);
    }
}
