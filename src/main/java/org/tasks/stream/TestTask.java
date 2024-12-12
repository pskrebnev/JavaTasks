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

public class TestTask {

  static String s = "huhbabadaadababhadkhkjhkkjhiuyiuyihghfhfhfhfdtre";
  static String txt2 = "This  proposal sets   out to implement several kinds"
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
  private static List<Integer> myList = Arrays.asList(1, 2, 3, 4, 5);
  private static List<Integer> scoreList
      = Arrays.asList(10, 12, 30, 14, 35, 72, 11, 10, 45, 50, 53, 60, 75, 80, 98, 100);


  // "bab", "aba"
  public static void main(String[] args) {
//    List<String> palindromes = findPalindromesNO(s);
//    List<String> palindromes = findAllPalindromes(txt2);
//    System.out.println(findLongestStringWithLength(palindromes));

//    System.out.println(qtyAppearance(cleanUp(txt2), "rt"));
//    List<Integer> arr = Arrays.asList(1, 2, 1, 2, 1, 3, 3, 3, 3, 1, 2);
//    sockMerchant(arr).forEach((key, value) -> System.out.println(key + " = " + value));
//    System.out.println(findIndexes(myList, 5));
    countScores(scoreList).forEach((k, v) -> System.out.println(k + " -> " + v));
  }

  private static Map<String, Long> countScores(List<Integer> listInts) {
    Predicate<Integer> isInScope = num -> num >= 0 && num <= 100;

    return listInts.stream()
        .filter(isInScope)
        .map(score -> {
          if (score <= 30) return "D";
           else if (score <= 40) return "C";
           else if (score <= 60) return "B";
           else return "A";
        })
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Long> comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (e1, e2) -> e1,
            LinkedHashMap::new
        ));
  }

  // If you need both the string and its length:
  public static Map.Entry<String, Integer> findLongestStringWithLength(List<String> list) {
    return list.stream()
        .map(s -> Map.entry(s, s.length()))
        .max(Map.Entry.comparingByValue())
        .orElse(Map.entry("", 0));
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

  private static void printStr() {
    IntStream a = IntStream.range(0, 5);
    IntStream b = IntStream.rangeClosed(7, 10);

    IntStream.concat(a, b)
        .mapToObj(i -> "Number " + i)
        .forEach(System.out::println);
  }

  private static int findMaxStr(List<Integer> intList) {
    return intList.stream()
        .mapToInt(Integer::intValue)
        .max()
        .orElseThrow();
  }

  private static int findMax(List<Integer> integerList) {
    int maxInt = Integer.MIN_VALUE;

    for (int s : integerList) {
      if (s > maxInt) {
        maxInt = s;
      }
    }
    return maxInt;
  }

  // Given: Given an array of integers nums and an integer target, return indices of the
  // two numbers such that they add up to target.
  //
  //You may assume that each input would have exactly one solution, and you may not use
  // the same element twice.
  // URL: https://leetcode.com/problems/two-sum/description/
  private static List<List<Integer>> findIndexes(List<Integer> list, int target) {
    return IntStream.range(0, list.size())
        .boxed()
        .flatMap(i ->
            IntStream.range(i + 1, list.size())
                .filter(j -> list.get(i) + list.get(j) == target)
                .mapToObj(j -> Arrays.asList(i, j)))
        .collect(Collectors.toList());
  }

  private static List<List<Integer>> findIndexesOptimal(List<Integer> list, int target) {
    List<List<Integer>> result = new ArrayList<>();
    Map<Integer, Integer> numMap = new HashMap<>();

    for (int i = 0; i <list.size(); i++) {
      int compl = target - list.get(i);
      if (numMap.containsKey(compl)) {
        result.add(Arrays.asList(numMap.get(compl), i));
      }
      numMap.put(list.get(i), i);
    }
    return result;
  }
}
