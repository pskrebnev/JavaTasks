package org.manipulations.file;

import com.google.common.collect.ImmutableList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.objects.game.Game;


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

  public static ImmutableList<Game> getGames(List<String> list) {
    List<Game> gameList = new ArrayList<>();

    list
        .forEach(game -> {
          String[] match = game.split(",");
          String[] teams = match[0].split(":");
          String[] scores = match[1].split(":");

          String team1 = teams[0].trim();
          String team2 = teams[1].trim();
          int score1 = Integer.parseInt(scores[0].trim());
          int score2 = Integer.parseInt(scores[1].trim());

          gameList.add(new Game(
              team1
              , team2
              , score1
              , score2
          ));
        });

    return ImmutableList.copyOf(gameList);
  }
}
