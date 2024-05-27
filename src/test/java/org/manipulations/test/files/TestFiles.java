package org.manipulations.test.files;

import java.io.IOException;
import org.manipulations.file.ReadJson;
import org.manipulations.file.ReadTxtFile;
import org.junit.jupiter.api.Test;

public class TestFiles {

  @Test
  public void testReadingTxt() {
    ReadTxtFile.getRowResults().stream()
        .limit(5)
        .forEach(System.out::println);
  }

  @Test
  public void testReadingJson() throws IOException {
    ReadJson.getCarsList().stream()
        .limit(5)
        .forEach(System.out::println);
  }

}
