package org.smalltasks.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LongestStrWORepeating {

  // the original problem is here
  // https://leetcode.com/problems/longest-substring-without-repeating-characters/description/

  public static void main(String[] args) {
    String str1 = "aswgbysedrawg";
    String str2 = "adrswyhgbzqqwsrwg";
    String str3 =
        "We’ve collected over 30 essential Kotlin Multiplatform (KMP) and Compose Multiplatform learning materials. Browse by skill level to find tutorials, courses, and articles that fit your experience:\n"
            + "\n"
            + "\uD83C\uDF31 Beginner. Learn KMP and Compose fundamentals through official JetBrains and Google tutorials. Build simple apps using core libraries like Room, Ktor, and SQLDelight.\n"
            + "\n"
            + "\uD83C\uDF3F Intermediate. Develop real-world apps with shared ViewModels, Koin-based dependency injection, and clean architecture. Learn through courses by JetBrains and community educators.\n"
            + "\n"
            + "\uD83C\uDF33 Advanced. Progress to full-scale KMP engineering for backend and game development, with guidance on scaling architecture and adoption for large, multi-team projects.\n"
            + "\n"
            + "\uD83E\uDDE9 Library authors. Create and publish reusable KMP libraries. Learn API design, Dokka documentation, and Maven publishing with official JetBrains tooling and templates.";

//    System.out.println(findLongestUnique(str1));
//    System.out.println(findLongestUnique(str2));
    countWords(cleanUp(str3)).forEach((k, v) -> System.out.println(k + " -> " + v));
  }

  private static String findLongestUnique(String str) {
    // check if the string is empty or null
    if (str == null || str.isEmpty()) {
      return "";
    }

    Set<Character> seen = new HashSet<>();
    int start = 0;
    int maxStart = 0;
    int maxLength = 0;

    for (int end = 0; end < str.length(); end++) {
      char c = str.charAt(end);

      // If char is a duplicate, shrink a window from the left
      while (seen.contains(c)) {
        seen.remove(str.charAt(start));
        start++;
      }

      seen.add(c);

      // Update max if the current window is longer
      if (end - start + 1 > maxLength) {
        maxLength = end - start + 1;
        maxStart = start;
      }
    }

    return str.substring(maxStart, maxStart + maxLength);
  }

  private static Map<String, Long> countWords(String string) {
    return Arrays.stream(string.split(" "))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .filter(item -> item.getValue() > 2)
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  private static String cleanUp(String str) {
    return Pattern.compile("[^a-zA-Z0-9 ]")
        .matcher(str)
        .replaceAll(" ")
        .trim()
        .replaceAll(" +", " ")
        .toLowerCase();
  }
}
