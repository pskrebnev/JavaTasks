package org.patterns.factory;

public class AdvancedFactoryDemo {
  public static void main(String[] args) {
    System.out.println("=== Advanced Factory Pattern Demo ===\n");

    // Example 1: Using string-based factory method
    System.out.println("1. Creating vehicles using string types:");
    try {
      Vehicle car = VehicleFactory.createVehicle("car");
      Vehicle motorcycle = VehicleFactory.createVehicle("MOTORCYCLE");
      Vehicle truck = VehicleFactory.createVehicle("Truck");

      System.out.println("Created: " + car.getType());
      System.out.println("Created: " + motorcycle.getType());
      System.out.println("Created: " + truck.getType());
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    System.out.println();

    // Example 2: Error handling for invalid types
    System.out.println("2. Testing error handling:");
    try {
      Vehicle invalidVehicle = VehicleFactory.createVehicle("AIRPLANE");
    } catch (IllegalArgumentException e) {
      System.out.println("Expected error: " + e.getMessage());
    }

    try {
      Vehicle nullVehicle = VehicleFactory.createVehicle((String) null);
    } catch (IllegalArgumentException e) {
      System.out.println("Expected error: " + e.getMessage());
    }

    System.out.println();

    // Example 3: Dynamic vehicle creation
    System.out.println("3. Creating all available vehicle types dynamically:");
    VehicleFactory.VehicleType[] availableTypes = VehicleFactory.getAvailableTypes();

    for (VehicleFactory.VehicleType type : availableTypes) {
      Vehicle vehicle = VehicleFactory.createVehicle(type);
      System.out.println("Created " + vehicle.getType() + ":");
      vehicle.start();
    }

    System.out.println();

    // Example 4: Using vehicles in a collection
    System.out.println("4. Using vehicles in an array:");
    Vehicle[] vehicles = {
        VehicleFactory.createVehicle(VehicleFactory.VehicleType.CAR),
        VehicleFactory.createVehicle(VehicleFactory.VehicleType.MOTORCYCLE),
        VehicleFactory.createVehicle(VehicleFactory.VehicleType.TRUCK)
    };

    // Start all vehicles
    System.out.println("Starting all vehicles:");
    for (Vehicle vehicle : vehicles) {
      vehicle.start();
    }

    // Stop all vehicles
    System.out.println("\nStopping all vehicles:");
    for (Vehicle vehicle : vehicles) {
      vehicle.stop();
    }
  }
}
