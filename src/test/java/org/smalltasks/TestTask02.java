package org.smalltasks;

public class TestTask02 {

  public static void main(String[] args) {
    // TODO: Print numbers 1 to 5 using for loop
    for (int i = 1; i < 6; i++) {
      System.out.println(i);
    }
    System.out.println("--for loop--");

    // TODO: Print numbers 1 to 5 using while loop
    int i = 1;
    while (i < 6) {
      System.out.println(i);
      i++;
    }
    System.out.println("--while loop--");

    // TODO: Print numbers 1 to 5 using do-while loop
    i = 1;
    do {
      System.out.println(i);
      i++;
    } while (i < 6);
  }

}
