package org.tasks.execution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdasAndMethodReferences {

  public static void main(String[] args) {
//    staticMR();
    boundMR();

  }

  public static void staticMR() {
    List<Integer> listInt = Arrays.asList(1, 2, 7, 4, 5);
    Consumer<List<Integer>> sortListLambda = list -> Collections.sort(list);

    System.out.println("Before sorting: " + listInt);

    sortListLambda.accept(listInt);
    System.out.println("After sorting: " + listInt);

    Collections.shuffle(listInt);
    System.out.println("Before sorting: " + listInt);

    Consumer<List<Integer>> sortListMR = Collections::sort;
    sortListMR.accept(listInt);
    System.out.println("After sorting: " + listInt);
  }

  public static void boundMR() {

    String name = "Mr. Joe Bloggs";
    Predicate<String> isStartedWithLambda = pre -> name.startsWith(pre);

    System.out.println("Started with ? 'Mr.' = " + isStartedWithLambda.test("Mr."));
    System.out.println("Started with ? 'Ms.' = " + isStartedWithLambda.test("Ms."));

    Predicate<String> isStartedWithMR = name::startsWith;

    System.out.println("Started with ? 'Mr.' = " + isStartedWithMR.test("Mr."));
    System.out.println("Started with ? 'Ms.' = " + isStartedWithMR.test("Ms."));
  }

  public static void unboundMR() {

  }

  public static void constructorMR() {

  }


}
