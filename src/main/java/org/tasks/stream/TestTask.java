package org.tasks.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestTask {

  static String s = "huhbabadaadababhadkhkjhkkjhiuyiuyihghfhfhfhfdtre";

  // "bab", "aba"
  public static void main(String[] args) {
//    List<String> palindromes = findPalindromesNO(s);
    List<String> palindromes = findAllPalindromes(s);
    palindromes.forEach(System.out::println);
  }

  public static List<String> findAllPalindromes(String s) {
    return IntStream.range(0, s.length())
        .mapToObj(i ->
            IntStream.rangeClosed(0, s.length() - (i + 1))
                .mapToObj(j -> s.substring(j, j + i + 1))
                .filter(str -> str.length() > 2)
                .filter(TestTask::isPalindrome)
                .distinct()
                .toList())
        .flatMap(List::stream)
        .toList();
  }

  private static boolean isPalindrome(String str) {
    return str.contentEquals(new StringBuilder(str).reverse());
  }

  public static List<String> findPalindromesNotOptimized(String str) {
    List<String> listPalindromes = new ArrayList<>();
    for (int i = 0; i < str.length(); i++) {
      for (int j = i; j < str.length(); j++) {
        String s = str.substring(i, j);
        if (s.length() > 2 && isPalindrome(s)) {
          listPalindromes.add(s);
        }
      }
    }
    return listPalindromes;
  }
}
