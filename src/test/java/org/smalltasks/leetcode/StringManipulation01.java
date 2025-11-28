package org.smalltasks.leetcode;

import java.util.Arrays;
import java.util.List;

// Original problem is here https://leetcode.com/problems/backspace-string-compare/description/

public class StringManipulation01 {

  public static void main(String[] args) {

    System.out.println(processString("#######a#vy#ny"));
    System.out.println(processString("#######a##"));
    System.out.println(processString("as#######a#vy#ny"));

  }

  private static String processString(String str) {
    StringBuilder sb = new StringBuilder();
    String tempStr = str.replaceAll("[##]+", "#");
    List<String> listStr = Arrays.stream(tempStr.split("")).toList();

    for (int i = listStr.size() - 1; i >= 0; i--) {
      String s = listStr.get(i);
      if (s.equals("#")) {
        i--;
      } else {
        sb.append(s);
      }
    }

    return sb.reverse().toString();
  }
}
