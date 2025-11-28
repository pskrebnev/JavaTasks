package org.smalltasks.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MeetTargetSum {

  //  Given an array of integers, return the indices
  //  of the two numbers that add up to a given target.
  //  https://leetcode.com/problems/two-sum/description/
  public static void main(String[] args) {
    int target = 9;
    int theArray[] = {2, 5, 6, 9, 8, 4, 5, 6, 9, 8, 2, 3, 4, 1, 9, 7, 5, 3};
//    findIndicies(theArray, target).forEach((k, v)
//        -> System.out.println(k + "->" + v));

    findNumOptimized(theArray, target).forEach((k, v)
        -> System.out.println(k + "->" + v));
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

  private static Map<Integer, Map<Integer, Integer>> findIndOptimized1(int[] array, int initNum) {
    int index = 0; // index for Map
    Map<Integer, Map<Integer, Integer>> indicies = new HashMap<>(); // final Map
    Map<Integer, Integer> accum = new HashMap<>(); // temporary Map
    int temp; //
    int toFind; // calculated num

    for (int i = 0; i < array.length; i++) {
      temp = array[i];
      toFind = initNum - temp;
      accum.put(i, temp);

      if (accum.containsValue(toFind)) {
        indicies.put(index, accum);
        index++;
      }
    }

    return indicies;
  }

  private static Map<Integer, Map<Integer, Integer>> findNumOptimized(int[] array, int target) {
    Map<Integer, Map<Integer, Integer>> indexMap = new HashMap<>();
    Map<Integer, Integer> accum = new HashMap<>();
    int index = 0;
    int temp; // to store var during running through the array
    int numToFind;

    for (int i = 0; i < array.length; i++) {
      temp = array[i];
      numToFind = target - temp;

      if (accum.containsKey(numToFind)) {
        Map<Integer, Integer> tempMap = new HashMap<>();
        int j = accum.get(numToFind); // get the second index

        tempMap.put(j, i);
        indexMap.put(index, tempMap);
        index++;
      }

      accum.put(temp, i);
    }

    return indexMap;
  }
}
