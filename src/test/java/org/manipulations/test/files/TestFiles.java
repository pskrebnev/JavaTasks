package org.manipulations.test.files;

import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.manipulations.file.reading.ReadCsv;
import org.manipulations.file.reading.ReadJson;
import org.manipulations.file.reading.ReadTxtFile;
import org.junit.jupiter.api.Test;
import org.objects.game.UtilGames;

public class TestFiles {

  @Test
  public void testReadingTxt() {
    ReadTxtFile.getGames(ReadTxtFile.getRowResults()).stream()
        .limit(5)
        .forEach(System.out::println);
  }

  @Test
  public void testReadingJson() throws IOException {
    ReadJson.getCarsList().stream()
        .limit(5)
        .forEach(System.out::println);
  }

  @Test
  public void testReadingCsv() {
    ReadCsv.getPeople().stream()
        .skip(10)
        .limit(5)
        .forEach(System.out::println);
  }

  @Test
  public void testWinnersList() {
    System.out.println("Winners list -- >");
    UtilGames.getWinnersList().stream()
        .limit(5)
        .forEach(System.out::println);
  }

  @Test
  public void testWinnersMap() {
    UtilGames.getWinsMapSorted(UtilGames.getWinnersList()).entrySet()
        .forEach(System.out::println);
  }
}