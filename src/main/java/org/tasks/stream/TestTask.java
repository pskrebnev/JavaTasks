package org.tasks.stream;

import java.util.Optional;
import java.util.stream.IntStream;

public class TestTask {

  static String s = "huhbabadaadababhad";
  // "bab", "aba"
  public static void main(String[] args) {
    String longestPalindrome = findLongestPalindrome(s);
    System.out.println(longestPalindrome);
  }

  public static String findLongestPalindrome(String s) {
    return IntStream.range(0, s.length())
        .mapToObj(i ->
            IntStream.rangeClosed(0, s.length() - (i + 1))
                .mapToObj(j -> s.substring(j, j + i + 1))
                .filter(TestTask::isPalindrome)
                .findFirst()
        )
        .flatMap(Optional::stream)
        .reduce((first, second) -> second)
        .orElse("");
  }

  private static boolean isPalindrome(String str) {
    return str.contentEquals(new StringBuilder(str).reverse());
  }
}
