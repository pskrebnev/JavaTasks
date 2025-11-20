package org.patterns.facade;

/**
 * Theater Lights - Part of the complex subsystem
 */
public class TheaterLights {
  private int brightness = 100;

  public void on() {
    brightness = 100;
    System.out.println("Theater lights are on at full brightness");
  }

  public void off() {
    brightness = 0;
    System.out.println("Theater lights are off");
  }

  public void dim(int level) {
    this.brightness = level;
    System.out.println("Theater lights dimmed to " + level + "%");
  }
}




