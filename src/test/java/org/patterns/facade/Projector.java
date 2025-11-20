package org.patterns.facade;

/**
 * Projector - Part of the complex subsystem
 */
public class Projector {

  public void on() {
    System.out.println("Projector is turning on...");
  }

  public void off() {
    System.out.println("Projector is turning off...");
  }

  public void wideScreenMode() {
    System.out.println("Projector setting to widescreen mode...");
  }
}


