package org.smalltasks.coderbyte;

import java.util.Stack;

public class Task03 {

  public static void main(String[] args) {

    String p1 = "([]{})";
    String p2 = "((({{{}}})))";
    String p3 = "([((}]))";

    System.out.println("For p1 -> " + isValidParentheses(p1));
    System.out.println("For p2 -> " + isValidParentheses(p2));
    System.out.println("For p3 -> " + isValidParentheses(p3));

  }

  //  Write a method that determines if a string containing only parentheses characters
  //  `()`, `{}`, `[]` is valid. A string is valid if:
  //- Open brackets are closed by the same type of brackets
  //- Open brackets are closed in the correct order
  //- Every closing bracket has a corresponding opening bracket
  private static boolean isValidParentheses(String pr) {
    if (pr == null || pr.length() % 2 != 0) {
      return false;
    }

    Stack<Character> stack = new Stack<>();

    for (char c : pr.toCharArray()) {
      // opening brackets
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else if (c == ')' || c == '}' || c == ']') {
        if (stack.empty()) {
          return false;
        }

        char top = stack.pop();

        // checking for closed brackets
        if ((c == ')' && top != '(') ||
            (c == '}' && top != '{') ||
            (c == ']' && top != '[')) {
          return false;
        }
      }
    }

    // is a stack empty?
    return stack.empty();
  }

}
