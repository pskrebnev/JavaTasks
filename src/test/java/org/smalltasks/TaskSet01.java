package org.smalltasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

public class TaskSet01 {
  // 1. read file from source and put it in a String
  // 2. read file from source and put it in a List
  // 3. You have a String. Revert it
  // 4. You have a String. Count the frequency of words. Map<String, Long>
  // 5. You have a String. Count the length of words and sort it. Map<String, Long>
  // 6. You have a String. Count the frequency of letters and sort in alphabet order. Map<String, Long>
  // 7. Filter words from a task 5 and print only > 6
  // 8. Find occurrencies of a phrase in a String

  private String fileName1 = "source.txt";
  private String filePath1 = "src/main/resources/nio/";
  private String fileName2 = "results.txt";
  private String filePath2 = "src/main/resources/";
  private String justText = "It has multiple features like support for Cross browser, Cross Platform and Cross language. It can also be used to perform mobile web testing by using the native emulation of Google Chrome for Android and Mobile Safari.";
  private List<Integer> scoreList = Arrays.asList(90, 54, 6, 68, 52, 48, 17, 44, 39, 2, 40, 44, 33,
      72, 64, 78, 6, 87, 28, 82, 99, 67, 18, 71, 90, 32, 90, 72, 44, 31, 56, 6, 23, 20, 64, 17, 4,
      10, 74, 73, 72, 81, 28, 5, 2, 13, 23, 93, 42, 63, 6, 67, 34, 11, 14, 63, 66, 80, 27, 38, 44,
      26, 38, 54, 89, 49, 31, 1, 25, 40, 44, 6, 2, 0, 31, 65, 96, 93, 75, 87, 87, 60, 94, 0, 19, 55,
      26, 67, 65, 39, 92, 81, 57, 24, 44, 40, 45, 92, 31, 82, 78, 98, 4, 94, 48, 69, 59, 44, 96, 31,
      17, 83, 91, 1, 86, 94, 0, 49, 52, 48, 97, 80, 76, 28, 19, 50, 22, 88, 82, 57, 53, 70, 78, 21,
      94, 85, 98, 60, 54, 24, 66, 6, 40, 54, 28, 19, 3, 92, 37, 14, 85, 88, 80, 55, 28, 100, 94, 11,
      36, 82, 6, 66, 55, 74, 59, 85, 59, 80, 64, 91, 51, 22, 8, 53, 45, 9, 46, 94, 24, 83, 99, 20,
      90, 83, 50, 20, 23, 88, 68, 6, 54, 16, 51, 69, 1, 25, 42, 35, 7, 98);

  // 1. read file from source and put it in a String
  @Test
  public void task01() throws IOException {
    String simpleText;

    try (Stream<String> rows = Files.lines(Paths.get(filePath1 + fileName1))) {
      simpleText = rows.collect(Collectors.joining());
    }

    System.out.println(simpleText);
  }

  // 2. read file from source and put it in a List
  @Test
  public void task02() throws IOException {
    List<String> lst;

    try (Stream<String> rows = Files.lines(Paths.get(filePath2 + fileName2))) {
      lst = rows.toList();
    }

    lst.forEach(System.out::println);
  }

  // 3. You have a String. Revert it
  @Test
  public void task03() {

    StringBuilder sb = new StringBuilder(justText);
    System.out.println(sb.reverse());
  }

  // 4. You have a String. Count the frequency of words. Map<String, Long>
  @Test
  public void task04() throws IOException {
    String theText;

    try (Stream<String> rows = Files.lines(Paths.get(filePath1 + fileName1))) {
      theText = cleanUpText(rows.collect(Collectors.joining()));
    }

    Arrays.stream(theText.split(" "))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(item -> System.out.println(item.getKey() + " = " + item.getValue()));
  }

  // 5. You have a String. Count the length of words and sort it. Map<String, Long>
  // 7. Filter words from a task 5 and print only > 8
  @Test
  public void task05() throws IOException {
    Predicate<Entry<String, Long>> more8 = item -> item.getValue() > 8;

    Arrays.stream(cleanUpText(getTextFromFile(filePath1 + fileName1))
            .split("\\s"))
        .collect(Collectors.toMap(
            Function.identity(),
            word -> (long) word.length(),
            (oldValue, newValue) -> oldValue, LinkedHashMap::new
        ))
        .entrySet().stream()
        .filter(more8)
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(item -> System.out.println(item.getKey() + " = " + item.getValue()));
  }

  // 6. You have a String. Count the frequency of letters and sort in alphabet order. Map<String, Long>
  @Test
  public void task06() throws IOException {
    String str = cleanUpText(getTextFromFile(filePath1 + fileName1));
    Predicate<String> notSpace = c -> !c.equals(" ");

    str.chars()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .filter(notSpace)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(item -> System.out.println(item.getKey() + " = " + item.getValue()));
  }

  // 8. Find occurrencies of a phrase in a String
  @Test
  public void task08() throws IOException {
    String txt = cleanUpText(getTextFromFile(filePath1 + fileName1));
    String txtToFind = "ic";

    int count = txt.chars()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining())
        .split(txtToFind, -1)
        .length - 1;

    System.out.println("Text '" + txtToFind + "' appears " + count + " times.");
  }

  @Test
  public void pringStr() {
    getScoresQty(getScores(scoreList))
        .forEach((k, v) -> System.out.println(k + " -> " + v));
  }

  @Test
  public void findOccur() {
    String simpleText = "There aren’t already more 10x professionals because, in many roles,"
        + " the gap between the best and the average worker has a ceiling. No matter how athletic"
        + " a supermarket checkout clerk is, they’re not likely to scan groceries so fast that"
        + " customers get out of the store 10x faster. Similarly, even the best doctor is"
        + " unlikely to make patients heal 10x faster than an average one (but to a sick patient,"
        + " even a small difference is worth a lot). In many jobs, the laws of physics"
        + " place a limit on what any human or AI can do (unless we completely"
        + " reimagine that job).";

    getStat(simpleText).forEach((key, val) -> System.out.println(key + " = " + val));
  }

  private String cleanUpText(String s) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(s)
        .replaceAll(" ")
        .trim()
        .replaceAll(" +", " ")
        .toLowerCase();
  }

  private String getTextFromFile(String fullPathToText) throws IOException {
    try (Stream<String> rows = Files.lines(Paths.get(fullPathToText))) {
      return rows.collect(Collectors.joining());
    }
  }

  // 0 to 30 -> "D"
  // 31 to 60 -> "C"
  // 61 to 95 -> "B"
  // 96 to 100 -> "A"
  private List<String> getScores(List<Integer> listInt) {
    return listInt.stream()
        .map(score -> {
          if (score >= 0 && score <= 30) {
            return "D";
          } else if (score >= 31 && score <= 60) {
            return "C";
          } else if (score >= 61 && score <= 96) {
            return "B";
          }
          return "A";
        })
        .collect(Collectors.toList());
  }

  private Map<String, Integer> getScoresQty(List<String> scores) {
    return scores.stream()
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.collectingAndThen(
                Collectors.counting(),
                Long::intValue
            )
        ));
  }

  private Map<String, Integer> getScQt(List<String> scores) {
    return scores.stream()
        .collect(Collectors.groupingBy(
            s -> s,
            Collectors.summingInt(s -> 1)
        ));
  }

  private Map<String, Long> getStat(String txt) {
    return txt.chars()
        .mapToObj(ch -> (char) ch)
        .filter(s -> !s.equals(' '))
        .map(Object::toString)
        .map(String::toLowerCase)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (i1, i2) -> i1,
            LinkedHashMap::new
        ));
  }
}
