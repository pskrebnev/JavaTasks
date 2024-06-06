package org.tasks.execution;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class LambdasAndMethodReferences {

  public static void main(String[] args) {
    staticMR();

  }

  public static void staticMR() {
    List<Integer> listInt = Arrays.asList(1, 2, 7, 4, 5);
    Consumer<List<Integer>> consIntLambda = list -> Collections.sort(list);

    System.out.println("Before sorting: " + listInt);

    consIntLambda.accept(listInt);
    System.out.println("After sorting: " + listInt);

    Collections.shuffle(listInt);
    System.out.println("Before sorting: " + listInt);

    Consumer<List<Integer>> consIntMR = Collections::sort;
    consIntMR.accept(listInt);
    System.out.println("After sorting: " + listInt);
  }

  public static void boundMR() {

  }

  public static void unboundMR() {

  }

  public static void constructorMR() {

  }


}
