package org.manipulations.file.reading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadTest {

  private static String cleanUpText(String str) {
    return Pattern.compile("[^a-zA-Z]")
        .matcher(str)
        .replaceAll(" ")
        .trim()
        .replaceAll(" +", " ")
        .toLowerCase();
  }

  private static final String theText = "Frontier intelligence at 2x the speed\n"
      + "Claude 3.5 Sonnet sets new industry benchmarks for graduate-level reasoning (GPQA), undergraduate-level knowledge (MMLU), and coding proficiency (HumanEval). It shows marked improvement in grasping nuance, humor, and complex instructions, and is exceptional at writing high-quality content with a natural, relatable tone.\n"
      + "\n"
      + "Claude 3.5 Sonnet operates at twice the speed of Claude 3 Opus. This performance boost, combined with cost-effective pricing, makes Claude 3.5 Sonnet ideal for complex tasks such as context-sensitive customer support and orchestrating multi-step workflows.\n"
      + "\n"
      + "In an internal agentic coding evaluation, Claude 3.5 Sonnet solved 64% of problems, outperforming Claude 3 Opus which solved 38%. Our evaluation tests the model’s ability to fix a bug or add functionality to an open source codebase, given a natural language description of the desired improvement. When instructed and provided with the relevant tools, Claude 3.5 Sonnet can independently write, edit, and execute code with sophisticated reasoning and troubleshooting capabilities. It handles code translations with ease, making it particularly effective for updating legacy applications and migrating codebases.";

  public static void main(String[] args) throws IOException {
//    String cleanText = cleanUpText(theText);
//    System.out.println(countSimilarWords(cleanText));
    System.out.println(readFromFile());
  }

  private static Map<String, Long> countSimilarWords(String text) {
    return Arrays.stream(text.split("\\s+"))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ));
  }

  private static List<String> readFromFile() throws IOException {
    String fileName = "results.txt";
    String filePath = "src/main/resources/";

    try (Stream<String> rows = Files.lines(Paths.get(filePath + fileName))) {
      return rows.collect(Collectors.toList());
    }
  }

}
