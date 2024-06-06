package org.manipulations.test.files;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
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

  @Test
  public void testStringReverse() {
    String str = "SOLID principles";

    StringBuilder sb = new StringBuilder(str);
    System.out.println("LTR = '" + str + "'");
    System.out.println("RTL = '" + sb.reverse() + "'");
  }

  @Test
  public void testListWords() {
    String text = "SOLID principles with Java examples Implement"
        + " backend part for online store by the end of the course";

    Arrays.stream(text.split(" "))
        .map(String::toLowerCase)
        .collect(Collectors.toMap(
            Function.identity(),
            String::length,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEach(item -> System.out.println(item.getKey() + " = " + item.getValue()));
  }

  @Test
  public void testStringLength() {
    // find the phrase and its length
    String str = "Imagine we're building a restaurant management application"
        + " That application needs to store data about the company's employees and it"
        + " starts out by creating the following table of employees";

    Predicate<Entry<String, Integer>> moreFive = item -> item.getValue() > 5;

    Consumer<Entry<String, Integer>> printMapItem = item -> System.out.println(
        item.getKey() + " = " + item.getValue());

    Comparator<Entry<String, Integer>> compareValueReverse = Entry.comparingByValue(
        Comparator.reverseOrder());

    Arrays.stream(str.split(" "))
        .map(String::toLowerCase)
        .collect(Collectors.toMap(
            Function.identity(),
            String::length,
            (oldValue, newValue) -> oldValue, LinkedHashMap::new
        ))
        .entrySet().stream()
        .filter(moreFive)
        .sorted(compareValueReverse)
        .forEach(printMapItem);
  }

  @Test
  public void testOccurrence() {
    // counting the occurrence of str in big string
    String initStr = "The main purpose of database normalization is to avoid complexities"
        + ", eliminate duplicates, and organize data in a consistent way. In normalization"
        + ", the data is divided into several tables linked together with relationships."
        + "Database administrators are able to achieve these relationships by using primary keys"
        + ", foreign keys, and composite keys."
        + "To get it done, a primary key in one table, for example, employee_wages is related"
        + " to the value from another table, for instance, employee_data.\n"
        + "N.B.: A primary key is a column that uniquely identifies the rows of data in that table."
        + " It’s a unique identifier such as an employee ID, student ID"
        + ", voter’s identification number (VIN), and so on.";

    String toFind = "data";

    int count = initStr.chars()
        .mapToObj(c -> (char) c)
        .map(String::valueOf)
        .map(String::toLowerCase)
        .collect(Collectors.joining())
        .split(toFind, -1)
        .length - 1;

    System.out.println(count);
  }
}