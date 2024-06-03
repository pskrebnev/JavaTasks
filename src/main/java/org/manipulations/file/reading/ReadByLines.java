package org.manipulations.file.reading;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReadByLines {

  public static List<String> readLineByLine() throws IOException {

    String fileName = "results.txt";
    String filePath = "src/main/resources/";

    try (Stream<String> rows = Files.lines(Paths.get(filePath + fileName))) {

      return rows
          .collect(Collectors.toList());
    }
  }
}
