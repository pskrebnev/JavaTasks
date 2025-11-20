package org.smalltasks.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MeetTargetSum {

  //  Given an array of integers, return the indices
  //  of the two numbers that add up to a given target.
  public static void main(String[] args) {
    int target = 9;
    int theArray[] = {2, 5, 6, 9, 8, 4, 5, 6, 9, 8, 2, 3, 4, 1, 9, 7, 5, 3};

    findIndicies(theArray, target).forEach((k, v) -> System.out.println(k + "->" + v));
  }

  private static Map<Integer, Map<Integer, Integer>> findIndicies(int[] array, int num) {
    Map<Integer, Map<Integer, Integer>> indicies = new HashMap<>();
    int index = 0;

    for (int i = 0; i < array.length - 1; i++) {
      int s1 = array[i];

      for (int j = i + 1; j < array.length; j++) {
        int s2 = array[j];

        if (s1 + s2 == num) {
          Map<Integer, Integer> pair = new HashMap<>();
          pair.put(i, j);

          indicies.put(index, pair);
          index++;
        }
      }
    }

    return indicies;
  }
}
