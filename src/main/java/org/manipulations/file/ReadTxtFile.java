package org.manipulations.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ReadTxtFile {

  public static List<String> getRowResults() {

    List<String> rowList = new ArrayList<>();

    String fileName = "results.txt";

    ClassLoader cl = ReadTxtFile.class.getClassLoader();

    InputStream input = cl.getResourceAsStream(fileName);

    try {
      assert input != null;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {

        String line;

        while ((line = reader.readLine()) != null) {

          rowList.add(line);
        }
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return rowList;
  }
}
