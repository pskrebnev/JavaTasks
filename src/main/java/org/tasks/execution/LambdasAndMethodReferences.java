package org.tasks.execution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class LambdasAndMethodReferences {

  // Tasks from 'Java 21, Java 17, Java 11 and Advanced Java 8' training (on Udemy)
// link: https://luxoft.udemy.com/course/ocp11_from_oca8/
  public static void main(String[] args) {
//    staticMR();
//    boundMR();
    unboundMR();
  }

  // Task #1
  public static void staticMR() {
    Comparator<Integer> intComparatorAsc = Integer::compare;
    Comparator<Integer> intComparatorDesc = Comparator.reverseOrder();

    // a)
    List<Integer> listInt = Arrays.asList(1, 2, 7, 4, 5);

    // b) c)
    Consumer<List<Integer>> sortListLambda = list -> Collections.sort(list);

    // d)
    System.out.println("Before sorting: " + listInt);
    sortListLambda.accept(listInt);
    System.out.println("After sorting: " + listInt);

    // e)
    Collections.shuffle(listInt);
    System.out.println("Before sorting: " + listInt);

    // f)
    System.out.println("After sorting. List: " + sort(listInt));
    Collections.shuffle(listInt);
    System.out.println("After sorting Asc. List+Comparator: " + sort(listInt, intComparatorAsc));
    System.out.println("After sorting Desc. List+Comparator: " + sort(listInt, intComparatorDesc));
  }

  // Task #2
  public static void boundMR() {

    String name = "Mr. Joe Bloggs";

    Predicate<String> isStartedWithLambda = pre -> name.startsWith(pre);

    System.out.println("Started with ? 'Mr.' = " + isStartedWithLambda.test("Mr."));
    System.out.println("Started with ? 'Ms.' = " + isStartedWithLambda.test("Ms."));

    Predicate<String> isStartedWithMR = name::startsWith;

    System.out.println("Started with ? 'Mr.' = " + isStartedWithMR.test("Mr."));
    System.out.println("Started with ? 'Ms.' = " + isStartedWithMR.test("Ms."));
  }

  // Task #3
  public static void unboundMR() {
    String pre1 = "Mr.";
    String pre2 = "Ms.";
    String name = "Mr. Joe Bloggs";

    Predicate<String> isEmptyLambda = s -> s.isEmpty();

    System.out.println("Text empty? = " + isEmptyLambda.test(""));
    System.out.println("Text empty? = " + isEmptyLambda.test("xyz"));

    Predicate<String> isEmptyMR = String::isEmpty;

    System.out.println("Text empty? = " + isEmptyMR.test(""));
    System.out.println("Text empty? = " + isEmptyMR.test("xyz"));

    BiPredicate<String, String> startsWithLambda = (s1, s2) -> s1.startsWith(s2);

    System.out.println("Is started? (L) = " + startsWithLambda.test(name, pre1));
    System.out.println("Is started? (L) = " + startsWithLambda.test(name, pre2));

    BiPredicate<String, String> startsWithMR = String::startsWith;

    System.out.println("Is started? (MR) = " + startsWithMR.test(name, pre1));
    System.out.println("Is started? (MR) = " + startsWithMR.test(name, pre2));
  }

  private static List<Integer> sort(List<Integer> list
      , Comparator<Integer> comparator) {
    List<Integer> sortedList = new ArrayList<>(list);
    Collections.sort(sortedList, comparator);
    return sortedList;
  }

  private static List<Integer> sort(List<Integer> list) {
    List<Integer> sortedList = new ArrayList<>(list);
    Collections.sort(sortedList);
    return sortedList;
  }
}
