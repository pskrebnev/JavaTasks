package org.manipulations.test.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

public class TestReadingWriting {

  @Test
  public void testCreationFolderAndFile() {

    String propertiesRoot = "src/main/resources/";
    String folderRoot = "logs/data01/";
    String fileName = "/dataCalendar01.txt";

    Path path = Paths.get(propertiesRoot, folderRoot);

    try {
      Files.createDirectories(path);
      System.out.println("Directory created: " + path);
    } catch (IOException e) {
      System.out.println("Error creating directory: " + e.getMessage());
    }

    Path filePath = Paths.get(propertiesRoot, folderRoot + fileName);

    try {
      Files.createFile(filePath);
      System.out.println("File created: " + filePath);
    } catch (IOException e) {
      System.out.println("Error creating file: " + e.getMessage());
    }
  }
}
