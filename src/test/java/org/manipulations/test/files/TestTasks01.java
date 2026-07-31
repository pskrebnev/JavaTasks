package org.manipulations.test.files;

import java.util.Comparator;
import java.util.Locale;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

public class TestTasks01 {

  // count chars
  // revert string
  // count words
  @Test
  public void countChars() {

    String str = "Hello World".toLowerCase(Locale.ROOT);
    str.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream().sorted(Map.Entry.<Character, Long>comparingByKey().reversed())
        .forEach(item -> System.out.println(item.getKey() + "->" + item.getValue()));
  }
}
