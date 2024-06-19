package org.tasks.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathGeneral {

  public static void main(String[] args) {
    // current directory
    System.out.println(
        System.getProperty("user.dir"));   // C:\Users\skennedy\Documents\NetbeansProjects\Udemy

    // Path.of() is new to Java 11
    Path p1 = Path.of("lgc/logo.png");      // relative path
    Path p2 = Path.of("c:\\lgc\\logo.png"); // absolute path (Windows)
    Path p3 = Path.of("/lgc/logo.png");     // absolute path (Linux/Mac)

    // same as above except using varargs
    p1 = Path.of("lgc", "logo.png");
    p2 = Path.of("c:", "lgc", "logo.png");
    p3 = Path.of("/", "lgc", "logo.png");

    // Using Paths factory class - Paths.get()
    Path p4 = Paths.get("lgc/logo.png");      // relative path
    Path p5 = Paths.get("c:\\lgc\\logo.png"); // absolute path (Windows)
    Path p6 = Paths.get("/lgc/logo.png");     // absolute path (Linux/Mac)

    // using varargs
    p4 = Paths.get("lgc", "logo.png");
    p5 = Paths.get("c:", "lgc", "logo.png");
    p6 = Paths.get("/", "lgc", "logo.png");
  }

}
