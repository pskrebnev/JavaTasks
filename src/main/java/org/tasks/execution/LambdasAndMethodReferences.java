package org.tasks.execution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class LambdasAndMethodReferences {

  // Tasks from 'Java 21, Java 17, Java 11 and Advanced Java 8' training (on Udemy)
// link: https://luxoft.udemy.com/course/ocp11_from_oca8/
  public static void main(String[] args) {
    staticMR();
  }

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
