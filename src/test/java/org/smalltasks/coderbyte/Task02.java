package org.smalltasks.coderbyte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Task02 {

  public static void main(String[] args) {
    //    Write a method that rotates an N×N matrix 90 degrees clockwise
    //    in-place (without using extra space for another matrix).
//    int[][] m = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//    printMatrix(m);
//    System.out.println("---");
//    printMatrix(rotateMatrix(m));

    // Write a method that merges two sorted arrays into one sorted array.
    int[] arr1 = {1, 8, 5, 7, 4, 2};
    int[] arr2 = {2, 4, 6, 3};

    int[] arr3 = {1, 3, 2};
    int[] arr4 = {};
    System.out.println(mergeArrays(arr1, arr2));
    System.out.println(mergeArrays(arr3, arr4));


  }

  private static int[][] rotateMatrix(int[][] mat) {
    if (mat == null || mat.length == 0) {
      return mat;
    }

    int size = mat.length;

    // transpose
    for (int i = 0; i < size; i++) {
      for (int j = i; j < size; j++) {
        int temp = mat[i][j];
        mat[i][j] = mat[j][i];
        mat[j][i] = temp;
      }
    }

    // reverse
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size / 2; j++) {
        int temp = mat[i][j];
        mat[i][j] = mat[i][size - j - 1];
        mat[i][size - j - 1] = temp;
      }
    }

    return mat;
  }

  private static void printMatrix(int[][] m) {
    for (int[] ints : m) {
      for (int anInt : ints) {
        System.out.print(anInt + " ");
      }
      System.out.println();
    }
  }

  private static List<Integer>  mergeArrays(int[] a1, int[] a2) {
    List<Integer> m = new ArrayList<>();

    Arrays.stream(a1).forEach(m::add);
    Arrays.stream(a2).forEach(m::add);

    return m.stream()
        .distinct()
        .sorted()
        .toList();
  }
}
