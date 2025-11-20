package org.patterns.factory;

// Concrete Product - Truck
public class Truck implements Vehicle {
  @Override
  public void start() {
    System.out.println("Truck diesel engine started. Ready for heavy-duty work!");
  }

  @Override
  public void stop() {
    System.out.println("Truck engine stopped. Air brakes released.");
  }

  @Override
  public String getType() {
    return "Truck";
  }
}

