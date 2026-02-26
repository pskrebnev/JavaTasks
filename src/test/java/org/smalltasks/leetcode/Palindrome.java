package org.smalltasks.leetcode;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Palindrome {

  // the problem described in https://leetcode.com/problems/valid-palindrome-ii/description/ and
  // here https://leetcode.com/problems/reverse-linked-list-ii/description/ and
  // here https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

  public static void main(String[] args) {

    String expStr = "daerty";
    String anStr = "Erdayt";

//    System.out.println("Original is: " + expStr + ". Sorted is: " + sorted(expStr));

//    System.out.println(
//        "Is '" + expStr + "' and '" + anStr + "' anagram? -> " + isAnagram(expStr, anStr));

//    sortInt().forEach((k, v) -> System.out.println(k + "->" + v));
    separateByMod2().forEach((k, v) -> System.out.println(k + "->" + v));
  }

  private static boolean isAnagram(String strOriginal, String strExpected) {
    return sorted(strOriginal)
        .equals(sorted(strExpected));
  }

  private static String sorted(String str) {
    if (str.isBlank()) {
      return "";
    }

    return Arrays.stream(str.split(""))
        .map(String::toLowerCase)
        .sorted()
        .collect(Collectors.joining());
  }

  private static Map<Integer, Boolean> sortInt() {
    return IntStream.rangeClosed(1, 20)
        .boxed()
        .collect(Collectors.toMap(
            Function.identity(),
            n -> n % 2 == 0
        )).entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  private static Map<Integer, String> separateByMod2() {
    return IntStream.rangeClosed(1, 20)
        .boxed()
        .collect(Collectors.toMap(
            Function.identity(),
            n -> n % 2 == 0 ? "even" : "odd"));
  }
}
