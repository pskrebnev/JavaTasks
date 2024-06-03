package org.manipulations.test.files;

import java.io.IOException;
import java.util.List;
import org.manipulations.file.reading.ReadCsv;
import org.manipulations.file.reading.ReadJson;
import org.manipulations.file.reading.ReadTxtFile;
import org.junit.jupiter.api.Test;
import org.objects.game.Game;
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
    UtilGames.getWinnersList().stream()
        .limit(10)
        .forEach(System.out::println);
  }

  @Test
  public void testWinnersMap() {
    UtilGames.getWinnsMap(UtilGames.getWinnersList()).entrySet()
        .forEach(System.out::println);
  }
}
