package org.tasks.stream;

import java.util.List;
import java.util.stream.IntStream;

public class TestTask {

  static String s = "huhbabadaadababhadkhkjhkkjhiuyiuyihghfhfhfhfdtre";

  // "bab", "aba"
  public static void main(String[] args) {
    List<String> palindromes = findAllPalindromes(s);
    palindromes.forEach(System.out::println);
  }

  public static List<String> findAllPalindromes(String s) {
    return IntStream.range(0, s.length())
        .mapToObj(i ->
            IntStream.rangeClosed(0, s.length() - (i + 1))
                .mapToObj(j -> s.substring(j, j + i + 1))
                .filter(TestTask::isPalindrome)
                .filter(str -> str.length() > 2)
                .distinct()
                .toList()
        )
        .flatMap(List::stream)
        .toList();
  }

  private static boolean isPalindrome(String str) {
    return str.contentEquals(new StringBuilder(str).reverse());
  }
}
