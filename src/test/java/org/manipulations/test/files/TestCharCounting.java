package org.manipulations.test.files;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestCharCounting {

  // for the given phrase count all characters and print it
  @Test
  public void testCountCharacters() {

    String phrase = "Learn Java functional programing with Lambda  Streams. Learn the"
        + " exciting  powerful new features of Java 7 and Java 8";

    phrase.chars()
        .mapToObj(c -> (char) c)
        .map(Character::toLowerCase)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(item -> System.out.println(item.getKey() + " = " + item.getValue()));
  }
}
