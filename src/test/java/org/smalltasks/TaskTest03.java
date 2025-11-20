package org.smalltasks;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TaskTest03 {

  public static void main(String[] args) {
    String simpleText =
        "In today’s fast-evolving software landscape, delivering high-quality applications at scale is a constant challenge. Traditional API testing often involves writing complex scripts, maintaining boilerplate code, and dealing with brittle test suites. Enter Playwright MCP (Model Context Protocol) combined with Large Language Models (LLMs) — a revolutionary duo that simplifies API automation by enabling testers to write tests in plain English, reducing coding overhead, and enhancing test efficiency.\n"
            + "In this blog, we’ll explore how Playwright MCP, powered by LLMs like Claude, transforms API automation, with practical examples drawn from the ExecuteAutomation Playwright MCP repository.\n"
            + "\uD83D\uDC4CYou’ll be amazed at how smart the Playwright MCP server is.\n"
            + "For example, when making a PUT request, it doesn’t just update the data — it also shows you the before and after values, giving you full visibility into what changed. A great feature for debugging and validation!\n"
            + "Why Modern API Automation Needs a New Approach\n"
            + "API testing is critical for ensuring seamless communication between application components. However, traditional tools like Postman, RestAssured, or even Playwright’s native API testing capabilities come with challenges:\n"
            + "Code-Heavy Scripts: Writing and maintaining test scripts requires significant coding expertise.\n"
            + "Dynamic APIs: Modern APIs with complex payloads or authentication mechanisms (e.g., OAuth) are hard to test consistently.\n"
            + "Flaky Tests: Environmental issues or timing problems often lead to unreliable test results.\n"
            + "Accessibility: Non-technical team members, like QA analysts, struggle to contribute to test creation.\n"
            + "Playwright MCP, integrated with LLMs, addresses these pain points by enabling natural language-driven testing, where you describe test scenarios in plain text, and the AI translates them into executable API calls. This approach democratizes testing, boosts productivity, and aligns with the shift toward AI-driven development workflows.";

    String text = cleanText(simpleText);
    countWords(text).forEach((k, v) -> System.out.println(k + " -> " + v));
    System.out.println("--------------");
    countChars(text).forEach((k, v) -> System.out.println(k + " -> " + v));
    System.out.println("Pattern 're' occurs " + countOccurrences(text, "re") + " times");
  }

  // count occurrences of words
  private static Map<String, Long> countWords(String str) {
    return Arrays.stream(str.split(" "))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .filter(item -> item.getValue() > 2)
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  // count occurrences of chars
  private static Map<Character, Long> countChars(String st) {
    return st.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.<Character, Long>comparingByValue().reversed())
        .filter(item -> item.getValue() > 10)
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  private static int countOccurrences(String str, String pt) {
    return str.chars().mapToObj(c -> (char) c)
        .map(String::valueOf)
        .collect(Collectors.joining())
        .split(pt)
        .length - 1;
  }

  private static String cleanText(String st) {
//    Predicate<Character> isLetter = Character::isLetter;
//    Predicate<Character> isSpace = c -> c.equals(' ');
//    Predicate<Character> isValidChar = c -> "eyuioa".indexOf(c) != -1;
//    Pattern pattern = Pattern.compile("[a-zA-Z0-9]");
    Predicate<Character> isValidChar = str -> String.valueOf(str)
        .matches("[a-zA-Z0-9 ]");

    return st.chars()
        .mapToObj(c -> (char) c)
        .filter(isValidChar)
//        .filter(c -> isLetter.test(c) || isSpace.test(c))
        .map(Character::toLowerCase)
        .map(String::valueOf)
        .collect(Collectors.joining());
  }
}
