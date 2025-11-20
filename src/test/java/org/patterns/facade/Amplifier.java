package org.patterns.facade;

/**
 * Amplifier - Part of the complex subsystem
 */
public class Amplifier {
  private int volume = 0;

  public void on() {
    System.out.println("Amplifier is turning on...");
  }

  public void off() {
    System.out.println("Amplifier is turning off...");
  }

  public void setDvd(DVDPlayer dvd) {
    System.out.println("Amplifier setting DVD player...");
  }

  public void setSurroundSound() {
    System.out.println("Amplifier setting surround sound...");
  }

  public void setVolume(int level) {
    this.volume = level;
    System.out.println("Amplifier setting volume to " + level);
  }
}



