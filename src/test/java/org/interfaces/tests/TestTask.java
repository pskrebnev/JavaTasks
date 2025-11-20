package org.interfaces.tests;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestTask {

  // calculate the sum of the following string "123456789"
  public static String number = "123456789";
  public static String thePhrase = "We are on the interview meeting";

  public static void main(String[] args) {
    int theSum = number.chars()
        .map(Character::getNumericValue)
        .sum();
    System.out.println(theSum);

    Map<String, Long> charMap = thePhrase.chars()
        .mapToObj(s -> String.valueOf((char) s))
        .map(String::toLowerCase)
        .filter(s -> !s.equals(" "))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        )).entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));

    charMap.forEach(
        (key, value) -> System.out.println(key + ": " + value));
  }
}





