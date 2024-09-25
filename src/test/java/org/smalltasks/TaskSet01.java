package org.smalltasks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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

    lst.stream().forEach(System.out::println);
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
        )).entrySet().stream()
        .sorted(Map.Entry.comparingByValue(
            Comparator.reverseOrder()
        ))
        .forEach(entity -> System.out.println(entity.getKey() + " = " + entity.getValue()));
  }

  private String cleanUpText(String s) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(s)
        .replaceAll(" ")
        .trim()
        .replaceAll(" +", " ")
        .toLowerCase();
  }


}
