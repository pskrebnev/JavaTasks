package org.smalltasks.leetcode;

public class TrappingRainWater {

  // https://leetcode.com/problems/trapping-rain-water/description/
  public static void main(String[] args) {
    int[] array = {0, 1, 0, 3, 1, 0, 2, 0, 1, 0, 4};

//    System.out.println(countAreaBrutForce(array));
//    System.out.println(countAreaDynamicProgramming(array));
    System.out.println(countAreaTwoPointers(array));
  }

  private static int countAreaBrutForce(int[] array) {
    int n = array.length;
    int water = 0;

    for (int i = 0; i < n; i++) {
      int leftMax = 0, rightMax = 0;

      // Find max on the left
      for (int j = 0; j <= i; j++) {
        leftMax = Math.max(leftMax, array[j]);
      }

      // Find max on the right
      for (int j = i; j < n; j++) {
        rightMax = Math.max(rightMax, array[j]);
      }

      water += Math.min(leftMax, rightMax) - array[i];
    }

    return water;
  }

  private static int countAreaDynamicProgramming(int[] array) {
    int n = array.length;
    if (n == 0) {
      return 0;
    }

    int[] leftMax = new int[n];
    int[] rightMax = new int[n];

    // Fill leftMax
    leftMax[0] = array[0];
    for (int i = 1; i < n; i++) {
      leftMax[i] = Math.max(leftMax[i - 1], array[i]);
    }

    // Fill rightMax
    rightMax[n - 1] = array[n - 1];
    for (int i = n - 2; i >= 0; i--) {
      rightMax[i] = Math.max(rightMax[i + 1], array[i]);
    }

    // Calculate water
    int water = 0;
    for (int i = 0; i < n; i++) {
      water += Math.min(leftMax[i], rightMax[i]) - array[i];
    }

    return water;
  }

  private static int countAreaTwoPointers(int[] array) {
    int leftIndex = 0;
    int rightIndex = array.length - 1;
    int leftMaxBar = 0;
    int rightMaxBar = 0;
    int water = 0;

    while (leftIndex < rightIndex) {
      if (array[leftIndex] < array[rightIndex]) {
        // TODO
        if (array[leftIndex] >= leftMaxBar) {
          leftMaxBar = array[leftIndex];
        } else {
          water += leftMaxBar - array[leftIndex];
        }

        leftIndex++;
      } else {
        // TODO
        if (array[rightIndex] >= rightMaxBar) {
          rightMaxBar = array[rightIndex];
        } else {
          water += rightMaxBar - array[rightIndex];
        }

        rightIndex--;
      }
    }

    return water;
  }
}
