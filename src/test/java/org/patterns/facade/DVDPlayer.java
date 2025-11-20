package org.patterns.facade;

/**
 * DVD Player - Part of the complex subsystem
 */
public class DVDPlayer {
  private String movie;

  public void on() {
    System.out.println("DVD Player is turning on...");
  }

  public void off() {
    System.out.println("DVD Player is turning off...");
  }

  public void play(String movie) {
    this.movie = movie;
    System.out.println("DVD Player is playing: " + movie);
  }

  public void stop() {
    System.out.println("DVD Player stopped playing: " + movie);
  }

  public void eject() {
    System.out.println("DVD Player ejecting disc...");
  }
}

