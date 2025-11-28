package org.smalltasks.leetcode;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Palindrome {

  // the problem described in https://leetcode.com/problems/valid-palindrome-ii/description/ and
  // here https://leetcode.com/problems/reverse-linked-list-ii/description/ and
  // here https://leetcode.com/problems/flatten-a-multilevel-doubly-linked-list/description/

  public static void main(String[] args) {

    String expStr = "daerty";
    String anStr = "erdayt";

//    System.out.println("Original is: " + expStr + ". Sorted is: " + sorted(expStr));

    System.out.println(
        "Is '" + expStr + "' and '" + anStr + "' anagram? -> " + isAnagram(expStr, anStr));
  }

  private static boolean isAnagram(String strOriginal, String strExpected) {
    return sorted(strOriginal)
        .equals(sorted(strExpected));
  }

  private static String sorted(String str) {
    return Arrays.stream(str.split(""))
        .map(String::toLowerCase)
        .sorted()
        .collect(Collectors.joining());
  }
}
