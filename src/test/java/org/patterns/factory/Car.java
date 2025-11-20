package org.patterns.factory;

// Concrete Product - Car
public class Car implements Vehicle {
  @Override
  public void start() {
    System.out.println("Car engine started. Ready to drive!");
  }

  @Override
  public void stop() {
    System.out.println("Car engine stopped. Parking brake engaged.");
  }

  @Override
  public String getType() {
    return "Car";
  }
}


