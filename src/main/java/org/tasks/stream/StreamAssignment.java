package org.tasks.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamAssignment {

  public static void main(String[] args) {
//    task01();
    task02();
  }

  // QID 2.2023
  private static void task01() {
    System.out.println(IntStream.range(0, 5)
        .average()
        .orElse(Double.NaN));
  }

  // QID Q2.1762
  private static void task02() {
    List<Item> items = Arrays.asList(
        new Item(1, "Screw"),
        new Item(2, "Nail"),
        new Item(3, "Bolt")
    );

    items.stream()
        .sorted(Comparator.comparingInt(Item::getId).reversed())
        .forEach(item -> System.out.print(item.getName()));
  }

  private static void task03() {

  }

}
