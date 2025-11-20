package org.patterns.facade;

/**
 * Main class to test the Facade Design Pattern
 */
public class HomeTheaterTest {
  public static void main(String[] args) {
    Amplifier amp = new Amplifier();
    DVDPlayer dvd = new DVDPlayer();
    Projector projector = new Projector();
    TheaterLights lights = new TheaterLights();
    Screen screen = new Screen();

    HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, projector, lights, screen);

    homeTheater.watchMovie("Inception");
    System.out.println();
    homeTheater.endMovie();

    System.out.println();

    homeTheater.listenToMusic("The Beatles");
    System.out.println();
    homeTheater.stopMusic();
  }
}


