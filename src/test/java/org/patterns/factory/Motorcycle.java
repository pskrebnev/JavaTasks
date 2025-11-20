package org.patterns.factory;

// Concrete Product - Motorcycle
public class Motorcycle implements Vehicle {
  @Override
  public void start() {
    System.out.println("Motorcycle engine roaring! Ready to ride!");
  }

  @Override
  public void stop() {
    System.out.println("Motorcycle engine stopped. Kickstand down.");
  }

  @Override
  public String getType() {
    return "Motorcycle";
  }
}
