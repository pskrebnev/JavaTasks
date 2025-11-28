package org.smalltasks.leetcode;

public class ContainerWithTheMostWater {
  // https://leetcode.com/problems/container-with-most-water/description/
  // !move the smallest bar!

  public static void main(String[] args) {
    int[] bars = {1, 9, 100, 4, 99, 0, 7};

    System.out.println("Not Optimized -> " + findTheMostContainer(bars));
    System.out.println("Optimized -> " + findTheMostContainerOpt(bars));
  }

  private static int findTheMostContainer(int[] array) {
    int maxArea = 0;

    for (int i = 0; i < array.length - 1; i++) {
      int leftBar = array[i];

      for (int j = i + 1; j < array.length; j++) {
        int rightBar = array[j];
        int tempArea = Math.min(leftBar, rightBar) * (j - i);

        maxArea = Math.max(maxArea, tempArea);
      }
    }

    return maxArea;
  }

  private static int findTheMostContainerOpt(int[] array) {
    int maxArea = 0;
    int leftBar = 0;
    int rightBar = array.length - 1;
    int height, width;

    while (leftBar < rightBar) {
      width = rightBar - leftBar;
      height = Math.min(array[leftBar], array[rightBar]);
      int tempArea = width * height;
      maxArea = Math.max(maxArea, tempArea);

      if (array[leftBar] < array[rightBar]) {
        leftBar++;
      } else {
        rightBar--;
      }
    }

    return maxArea;
  }
}
