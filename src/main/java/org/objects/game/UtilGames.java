package org.objects.game;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.manipulations.file.reading.ReadTxtFile;

public class UtilGames {

  public static List<String> getWinnersList() {
    return ReadTxtFile.getGames(ReadTxtFile.getRowResults()).stream()
        .map(match -> {
          int sF = match.getScoreFirst();
          int sS = match.getScoreSecond();
          String tF = match.getTeamFirst();
          String tS = match.getTeamSecond();

          return (sF != sS && !tF.equals(tS)) ? (sF > sS ? tF : tS) : null;
        })
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
  }

  // returns Map<String, Long> where String = 'team', Long = 'number of wins' sorted in DESC order
  public static Map<String, Long> getWinsMapSorted(List<String> list) {

    return list.stream()
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new
        ));
  }
}
