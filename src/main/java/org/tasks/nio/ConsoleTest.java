package org.tasks.nio;

import java.io.Console;
import java.util.Arrays;

public class ConsoleTest {

  public static void main(String[] args) {
    Console console = System.console(); // not "new"
    if (console == null) {
      System.err.println("Console not available");
    } else {
      String name = console.readLine("Please enter your %s:", "name");
      console.format("Hello there %s%n", name);
      console.printf("Welcome."); // varargs (0 args is valid)
      console.writer().println(); // blank line

      char[] pwd = console.readPassword("Enter password (between %d and %d characters):", 4, 10);
      char[] pwdAgain = console.readPassword("Verify password: ");
      boolean pwdMatch = Arrays.equals(pwd, pwdAgain);
      if (pwdMatch) {
        console.printf("Passwords match!");
      } else {
        console.printf("Passwords do NOT match!");
      }
    }
  }
}
