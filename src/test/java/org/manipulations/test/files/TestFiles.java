package org.manipulations.test.files;

import org.manipulations.file.ReadTxtFile;
import org.junit.jupiter.api.Test;

public class TestFiles {

  @Test
  public void testReading() {
    ReadTxtFile.getRowResults().stream()
        .limit(5)
        .forEach(System.out::println);
  }
}
