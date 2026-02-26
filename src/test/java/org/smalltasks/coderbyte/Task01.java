package org.smalltasks.coderbyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Task01 {

  public static void main(String[] args) {
    String str1 = "aaaswwwweeaaaaa";
    String str2 = "asewtfaa";
    List<Integer> a = Arrays.asList(17, 5, 19);
    List<Integer> b = Arrays.asList(0, 2, 6);

    String str3 = "The full fix cycle. Initial implementation passed basic tests but validators"
        + " caught edge cases: race conditions in concurrent updates, ABA problem not fully"
        + " handled, retry backoff timing issues. Each rejection triggered fixes until all 48"
        + " tests passed with 91%+ coverage. A single agent would say \"done!\" after the first"
        + " implementation. Here, the adversarial tester actually runs concurrent requests, times"
        + " the retry backoff, and verifies conflict detection works under load. This is what"
        + " production-grade looks like. Not \"tests pass\" — validators reject until it actually"
        + " works. 5 iterations, each one fixing real bugs the previous attempt missed.";

//    System.out.println(revertStr("BorMoGlod"));
//    System.out.println("Original '" + str1 + "'. Compressed '" + compress(str1) + "'");
//    System.out.println("Original '" + str2 + "'. Compressed '" + compress(str2) + "'");
//    countWords(cleanUpText(str3)).forEach((k, v) -> System.out.println(k + "->" + v));
    System.out.println(countPattern(cleanUpText(str3), "as"));
    countChars(cleanUpText(str3)).forEach((k, v) -> System.out.println(k + "->" + v));
//    System.out.println(cleanUpText(str3));
    compareTriplets(a, b).forEach(System.out::print);
  }

  private static String revertStr(String st) {
    List<String> stReverted = new ArrayList<>();
    Arrays.stream(st.split(""))
        .forEach(stReverted::addFirst);

    return String.join("", stReverted);
  }

  //  Write a method that performs basic string compression using counts of repeated characters.
  //  If the compressed string is not smaller than the original, return the original string.
  private static String compress(String st) {
    if (st == null || st.length() <= 1) {
      return st;
    }

    StringBuilder compressed = new StringBuilder();
    int count = 1;
    char currentChar = st.charAt(0);

    for (int i = 1; i < st.length(); i++) {
      if (st.charAt(i) == currentChar) {
        count++;
      } else {
        // Current character and its count
        compressed.append(currentChar).append(count);
        currentChar = st.charAt(i);
        count = 1;
      }
    }

    // The last character
    compressed.append(currentChar).append(count);

    String result = compressed.toString();
    return result.length() < st.length() ? result : st;
  }

  // count chars
  private static Map<Character, Long> countChars(String str) {
    return str.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        )).entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  // count length of word
  private static Map<Long, Long> countWordsLength(String st) {
    return Arrays.stream(st.split(" "))
        .collect(Collectors.groupingBy(
            word -> Long.valueOf(word.length()),
            Collectors.counting()
        ));
  }

  // count words
  private static Map<String, Long> countWords(String s) {
    return Arrays.stream(s.split(" "))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ));
  }

  // find a qty string in a string
  private static int countPattern(String txt, String txtToFind) {
    return txt.chars()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining())
        .split(txtToFind).length - 1;
  }

  // only chars or digits. all lowercase
  private static String cleanUpText(String st) {
    Predicate<Character> isValidChar = s -> String.valueOf(s)
        .matches("[a-zA-Z ]");

    return st.chars()
        .mapToObj(c -> (char) c)
        .filter(isValidChar)
        .map(String::valueOf)
        .map(String::toLowerCase)
        .collect(Collectors.joining());
  }

  public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
    List<Integer> res = Arrays.asList(0, 0);

    for (int i = 0; i < b.size(); i++) {
      if (a.get(i) > b.get(i)) {
        res.set(0, res.get(0) + 1);
      } else if (a.get(i) < b.get(i)) {
        res.set(1, res.get(1) + 1);
      }
    }

    return res;
  }
}
