package org.tasks.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Comparator;

public class TestTask {

  static String s = "huhbabadaadababhadkhkjhkkjhiuyiuyihghfhfhfhfdtre";
  static String txt2 = "This     proposal sets         out to implement several kinds"
      + " of Quantum Vision Transformers (QViT) for High Energy Physics (HEP)"
      + " analysis at the Large Hadron Collider (LHC), inspired by the seminal"
      + " work of Cherrat et al., which introduces three innovative quantum transformer"
      + " architectures: Orthogonal Patch-wise Neural Network, Quantum Orthogonal Transformer,"
      + " and Quantum Compound Transformer. Despite the groundbreaking nature of their"
      + " research, the absence of released source code has left a significant gap"
      + " in practical application and reproducibility. Furthermore, attempts by last"
      + " year's contributors, Marçal Comajoan Cara and Eyup B. Unlu, to implement the"
      + " Quantum Orthogonal Transformer faced challenges; Marçal encountered training"
      + " issues due to NaN values, and Eyup's implementation did not proceed to the training"
      + " phase of transformers, highlighting the difficulties in realizing these models. This"
      + " project aims to address these gaps by developing QViT models within the TensorFlow"
      + " Quantum framework, leveraging this technology to bridge theoretical insights with"
      + " practical application, thereby marking a potential first in applying these models"
      + " for the analysis of particle jet images in HEP.";

  // "bab", "aba"
  public static void main(String[] args) {
//    List<String> palindromes = findPalindromesNO(s);
//    List<String> palindromes = findAllPalindromes(s);
//    palindromes.forEach(System.out::println);
//    System.out.println(qtyAppearance(cleanUp(txt2), "rt"));
    List<Integer> arr = Arrays.asList(1, 2, 1, 2, 1, 3, 3, 3, 3, 1, 2);
    sockMerchant(arr).forEach((key, value) -> System.out.println(key + " = " + value));

    // Test character frequency counter
    System.out.println("\nCharacter frequency in sample text:");
    countCharFrequency("Hello, World!").entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
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

  public static Map<Integer, Integer> sockMerchant(List<Integer> arr) {
    return arr.stream()
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.collectingAndThen(
                Collectors.counting(),
                Long::intValue
            )
        ))
        .entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> Math.round(entry.getValue() / 2)
        ));
  }

  private static int qtyAppearance(String source, String txtToFind) {
    return source.chars()
        .mapToObj(s -> (char) s)
        .map(String::valueOf)
        .collect(Collectors.joining())
        .split(txtToFind, -1)
        .length - 1;
  }


  private static String cleanUp(String s) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(s)
        .replaceAll(" ")
        .replaceAll(" +", " ")
        .toLowerCase();
  }

  private static int findMaxWithStream1(List<Integer> intList) {
    return intList.stream()
        .reduce(Integer::max)
        .orElseThrow();
  }

  private static int findMaxStr(List<Integer> intList) {
    return intList.stream()
        .mapToInt(Integer::intValue)
        .max()
        .orElseThrow();
  }

  private static int findMax(List<Integer> integerList) {
    int maxInt = Integer.MIN_VALUE;

    for (int i = 0; i < integerList.size(); i++) {
      int s = integerList.get(i);
      if (s > maxInt) {
        maxInt = s;
      }
    }
    return maxInt;
  }

  /**
   * Counts the frequency of each character in the input text.
   * 
   * @param text the input text to analyze
   * @return a map where keys are characters and values are their frequencies
   */
  public static Map<Character, Long> countCharFrequency(String text) {
    if (text == null || text.isEmpty()) {
      return new HashMap<>();
    }

    return text.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ));
  }
}
