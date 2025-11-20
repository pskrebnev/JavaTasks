package org.interfaces.tests;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestTask01 {

  public static void main(String[] args) {
    String str = "<Some very simple string>";

    String revStr = IntStream.range(0, str.length())
        .map(i -> str.length() - i - 1)
        .mapToObj(i -> String.valueOf(str.charAt(i)))
        .collect(Collectors.joining());

    System.out.println("Original str: " + str);
    System.out.println("Reverted str: " + revStr);

    Map<String, Long> qtyChar = str.chars()
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
            (e1, e2) -> e2,
            LinkedHashMap::new));

    qtyChar.forEach((k, v) -> System.out.println(k + ": " + v));
  }
}
