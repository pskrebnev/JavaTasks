# Facade Design Pattern in Java

This project demonstrates the implementation of the **Facade Design Pattern** using a Home Theater system example.

## What is the Facade Design Pattern?

The Facade Pattern provides a simplified interface to a complex subsystem. It defines a higher-level interface that makes the subsystem easier to use by hiding the complexities of the system and providing a simple interface to the client.

## Structure

### Subsystem Classes (Complex Components)
- **DVDPlayer.java** - Handles DVD operations (on/off, play, stop, eject)
- **Amplifier.java** - Manages audio amplification and volume control
- **Projector.java** - Controls projection display and screen modes
- **TheaterLights.java** - Manages lighting and dimming
- **Screen.java** - Controls screen up/down movements

### Facade Class
- **HomeTheaterFacade.java** - Provides simplified methods to operate the entire home theater system

### Test Class
- **HomeTheaterTest.java** - Demonstrates the usage of the facade pattern

## Key Benefits

1. **Simplification**: Clients only need to interact with the facade, not individual subsystem components
2. **Decoupling**: Reduces dependencies between clients and subsystem classes
3. **Layered Architecture**: Promotes layered system design
4. **Ease of Use**: Complex operations are encapsulated in simple method calls

## How to Run

1. Compile all Java files:
   ```
   javac *.java
   ```

2. Run the test:
   ```
   java HomeTheaterTest
   ```

## Example Usage

Without Facade (Complex):
```java
// Client would need to manage all these steps manually
lights.dim(10);
screen.down();
projector.on();
projector.wideScreenMode();
amp.on();
amp.setDvd(dvd);
amp.setSurroundSound();
amp.setVolume(5);
dvd.on();
dvd.play("Movie Name");
```

With Facade (Simple):
```java
// Client just calls one simple method
homeTheater.watchMovie("Movie Name");
```

## When to Use Facade Pattern

- When you want to provide a simple interface to a complex subsystem
- When there are many dependencies between clients and implementation classes
- When you want to layer your subsystems
- When you need to wrap a poorly designed collection of APIs with a single well-designed API
