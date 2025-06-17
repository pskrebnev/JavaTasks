package org.smalltasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ItemValidator {

  static List<Object> validItems = new ArrayList<>();
  static List<Object> invalidItems = new ArrayList<>();

  public static void main(String[] args) {
    List<Object> items = Arrays.asList(
        123123,     // correct
        "456456",   // correct
        789012,     // non-correct
        "12345",    // non-correct
        "1234567",  // non-correct
        "12a456",   // non-correct
        147258,     // non-correct
        "321321",   // correct
        null,       // non-correct
        "000000",    // correct
        156336      // correct
    );

    processItemsStream(items);

    validItems.forEach(System.out::println);
    System.out.println("---");
    invalidItems.forEach(System.out::println);
  }

  public static void processItemsStream(List<Object> items) {
    Map<Boolean, List<Object>> categorized = items.stream()
        .collect(Collectors.partitioningBy(ItemValidator::isValid));

    validItems = categorized.get(true);
    invalidItems = categorized.get(false);
  }

  private static boolean isValid(Object item) {

    String st = item == null ? "null" : item.toString();

    Predicate<String> is6Symbols = str -> str.length() == 6;
    Predicate<String> isAllDigits = str -> str.matches("\\d+");
    Predicate<String> isSumEquals = str ->
        getSumOfDigits(str, 0, 3) == getSumOfDigits(str, 3, 6);

    return is6Symbols.test(st)
        && isAllDigits.test(st)
        && isSumEquals.test(st);
  }

  private static int getSumOfDigits(String str, int start, int end) {
    return str.substring(start, end)
        .chars()
        .map(Character::getNumericValue)
        .sum();
  }
}
