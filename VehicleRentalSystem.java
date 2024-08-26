import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;

    public Vehicle(String vehicleId, String brand, String model) {
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculateRentalRate(int days) {
        return 0.0; 
    }
}

class Car extends Vehicle {
    private int seatingCapacity;
    private boolean isAutomatic;

    public Car(String vehicleId, String brand, String model, int seatingCapacity, boolean isAutomatic) {
        super(vehicleId, brand, model);
        this.seatingCapacity = seatingCapacity;
        this.isAutomatic = isAutomatic;
    }

    public double calculateRentalRate(int days) {
        return days * 1000.0; 
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public boolean isAutomatic() {
        return isAutomatic;
    }
}

class Bike extends Vehicle {
    private boolean isSportBike;

    public Bike(String vehicleId, String brand, String model, boolean isSportBike) {
        super(vehicleId, brand, model);
        this.isSportBike = isSportBike;
    }

    @Override
    public double calculateRentalRate(int days) {
        return days * 500.0; 
    }

    public boolean isSportBike() {
        return isSportBike;
    }
}

class Customer {
    private String customerId;
    private String name;
    private String contactInfo;

    public Customer(String customerId, String name, String contactInfo) {
        this.customerId = customerId;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}

class Rental {
    private Customer customer;
    private Vehicle vehicle;
    private int rentalDays;

    public Rental(Customer customer, Vehicle vehicle, int rentalDays) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalDays = rentalDays;
    }

    public double calculateTotalCost() {
        return vehicle.calculateRentalRate(rentalDays);
    }

    public Customer getCustomer() {
        return customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}

public class VehicleRentalSystem {
    private static List<Vehicle> vehicles = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeVehicles(); 

        int choice;
        do {
            System.out.print("\n----Vehicle Rental System----\n");

            System.out.print("\n1. Book a Vehicle For Rent");
            System.out.print("\n2. Available Vehicles");
            System.out.print("\n3. Rental Rates");
            System.out.print("\n4. Exit\n");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    bookVehicle(scanner);
                    break;
                case 2:
                    showAvailableVehicles();
                    break;
                case 3:
                    showRentalRates();
                    break;
                case 4:
                    System.out.println("Exiting... Thank you for using the Vehicle Rental System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 4);

        scanner.close();
    }

    private static void initializeVehicles() {
        vehicles.add(new Car("V001", "Toyota", "Camry", 5, true));
        vehicles.add(new Car("V002", "Honda", "Civic", 4, false));
        vehicles.add(new Bike("V003", "Yamaha", "YZF", true));
        vehicles.add(new Bike("V004", "Harley", "Sportster", false));
    }

    private static void bookVehicle(Scanner scanner) {
        System.out.print("\nEnter Customer ID: ");
        String customerId = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Contact Information: ");
        String contactInfo = scanner.nextLine();

        Customer customer = new Customer(customerId, customerName, contactInfo);

        System.out.print("Enter Vehicle ID to book: ");
        String vehicleId = scanner.nextLine();

        Vehicle vehicle = findVehicleById(vehicleId);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter Rental Days: ");
        int rentalDays = scanner.nextInt();

        Rental rental = new Rental(customer, vehicle, rentalDays);

        System.out.println("\nRental Details:");
        System.out.println("Customer: " + customer.getName());
        System.out.println("Contact Information: "+ contactInfo);
        System.out.println("Vehicle: " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("Total Cost: " + rental.calculateTotalCost());
    }

    private static void showAvailableVehicles() {
        System.out.println("\nAvailable Vehicles:");
        for (Vehicle vehicle : vehicles) {
            System.out.println("ID: " + vehicle.getVehicleId() + ", Brand: " + vehicle.getBrand() + ", Model: " + vehicle.getModel());
        }
    }

    private static void showRentalRates() {
        System.out.println("\nRental Rates:");
        System.out.println("Cars: 1000/- per day");
        System.out.println("Bikes: 500/- per day");
    }

    private static Vehicle findVehicleById(String vehicleId) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleId().equalsIgnoreCase(vehicleId)) {
                return vehicle;
            }
        }
        return null;
    }
}
