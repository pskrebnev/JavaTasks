package org.patterns.factory;

// Factory class
public class VehicleFactory {

  // Enum for vehicle types to avoid string comparison issues
  public enum VehicleType {
    CAR, MOTORCYCLE, TRUCK
  }

  // Factory method to create vehicles
  public static Vehicle createVehicle(VehicleType type) {
    switch (type) {
      case CAR:
        return new Car();
      case MOTORCYCLE:
        return new Motorcycle();
      case TRUCK:
        return new Truck();
      default:
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }
  }

  // Overloaded factory method that accepts string (for backward compatibility)
  public static Vehicle createVehicle(String type) {
    if (type == null || type.trim().isEmpty()) {
      throw new IllegalArgumentException("Vehicle type cannot be null or empty");
    }

    switch (type.toUpperCase()) {
      case "CAR":
        return new Car();
      case "MOTORCYCLE":
        return new Motorcycle();
      case "TRUCK":
        return new Truck();
      default:
        throw new IllegalArgumentException("Unknown vehicle type: " + type);
    }
  }

  // Method to get all available vehicle types
  public static VehicleType[] getAvailableTypes() {
    return VehicleType.values();
  }
}




