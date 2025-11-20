package org.patterns.facade;

/**
 * Home Theater Facade - The Facade class that provides a simplified interface
 * to the complex home theater subsystem
 */
public class HomeTheaterFacade {
  private Amplifier amp;
  private DVDPlayer dvd;
  private Projector projector;
  private TheaterLights lights;
  private Screen screen;

  public HomeTheaterFacade(Amplifier amp, DVDPlayer dvd, Projector projector,
      TheaterLights lights, Screen screen) {
    this.amp = amp;
    this.dvd = dvd;
    this.projector = projector;
    this.lights = lights;
    this.screen = screen;
  }

  /**
   * Simplified method to watch a movie - handles all the complex setup
   */
  public void watchMovie(String movie) {
    System.out.println("Get ready to watch a movie...");

    // Turn on all components
    lights.dim(10);
    screen.down();
    projector.on();
    projector.wideScreenMode();
    amp.on();
    amp.setDvd(dvd);
    amp.setSurroundSound();
    amp.setVolume(5);
    dvd.on();
    dvd.play(movie);

    System.out.println("Movie setup complete! Enjoy your movie: " + movie);
  }

  /**
   * Simplified method to end movie - handles all the complex shutdown
   */
  public void endMovie() {
    System.out.println("Shutting down movie theater...");

    // Turn off all components in reverse order
    dvd.stop();
    dvd.eject();
    dvd.off();
    amp.off();
    projector.off();
    screen.up();
    lights.on();

    System.out.println("Movie theater is now shut down.");
  }

  /**
   * Additional convenience method for listening to music
   */
  public void listenToMusic(String artist) {
    System.out.println("Get ready to listen to music...");

    lights.dim(20);
    amp.on();
    amp.setVolume(8);
    dvd.on();
    // Assuming the DVD player can also play music CDs
    dvd.play("Music by " + artist);

    System.out.println("Music setup complete! Enjoy listening to " + artist);
  }

  /**
   * Method to stop music and return to normal lighting
   */
  public void stopMusic() {
    System.out.println("Stopping music...");

    dvd.stop();
    dvd.off();
    amp.off();
    lights.on();

    System.out.println("Music stopped and lights restored.");
  }
}

