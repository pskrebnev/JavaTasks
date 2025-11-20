package org.smalltasks.leetcode;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestTasks {

  public static void main(String[] args) {
    String text1 = "We’ve collected over 30 essential Kotlin Multiplatform (KMP) and Compose"
        + " Multiplatform learning materials. Browse by skill level to find tutorials,"
        + " courses, and articles that fit your experience";

//    countChars(text1).forEach((k, v) -> System.out.println(k + " -> " + v));
    countWords(text1).forEach((k, v) -> System.out.println(k + " -> " + v));
  }

  // with the given text count all chars
  private static Map<Character, Long> countChars(String str) {
    return str.chars()
        .mapToObj(c -> (char) c)
        .filter(Character::isLetterOrDigit)
        .map(Character::toLowerCase)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        )).entrySet().stream()
        .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  private static Map<String, Long> countWords(String st) {
    return Arrays.stream(st.split("[\\s,().’]+"))
        .map(String::toLowerCase)
        .collect(
            Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            )
        ).entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }
}
