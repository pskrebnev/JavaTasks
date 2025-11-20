package org.patterns.factory;

public class FactoryPatternDemo {
  public static void main(String[] args) {
    // Create different kinds of vehicles using the factory
    Vehicle car = VehicleFactory.createVehicle(VehicleFactory.VehicleType.CAR);
    Vehicle motorcycle = VehicleFactory.createVehicle(VehicleFactory.VehicleType.MOTORCYCLE);
    Vehicle truck = VehicleFactory.createVehicle(VehicleFactory.VehicleType.TRUCK);

    // Perform actions on each vehicle
    car.start();
    motorcycle.start();
    truck.start();

    car.stop();
    motorcycle.stop();
    truck.stop();

    // Output types
    System.out.println("Car type: " + car.getType());
    System.out.println("Motorcycle type: " + motorcycle.getType());
    System.out.println("Truck type: " + truck.getType());
  }
}


