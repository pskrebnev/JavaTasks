package org.tasks.stream;

import java.util.Arrays;
import java.util.function.Predicate;

public class ArrayComparison {

  public static void main(String[] args) {
    // Sample arrays
    Integer[] array1 = {1, 2, 3, 4, 5};
    Integer[] array2 = {4, 5, 6, 7, 8};

    // Method 1: Check which elements from array1 are present in array2
    System.out.println("Elements from array1 present in array2:");
    Predicate<Integer> isContains = element -> Arrays.asList(array2).contains(element);
    Arrays.stream(array1)
        .filter(isContains)
        .forEach(System.out::println);

    // Method 2: Check which elements from array1 are NOT present in array2
    System.out.println("\nElements from array1 NOT present in array2:");
    Arrays.stream(array1)
        .filter(element -> !Arrays.asList(array2).contains(element))
        .forEach(System.out::println);

    // Method 3: Check if ANY elements from array1 are present in array2
    boolean anyMatch = Arrays.stream(array1)
        .anyMatch(element -> Arrays.asList(array2).contains(element));
    System.out.println("\nAny elements match: " + anyMatch);

    // Method 4: Check if ALL elements from array1 are present in array2
    boolean allMatch = Arrays.stream(array1)
        .allMatch(element -> Arrays.asList(array2).contains(element));
    System.out.println("All elements match: " + allMatch);

    // Method 5: Count how many elements from array1 are present in array2
    long matchCount = Arrays.stream(array1)
        .filter(element -> Arrays.asList(array2).contains(element))
        .count();
    System.out.println("Number of matching elements: " + matchCount);
  }
}
