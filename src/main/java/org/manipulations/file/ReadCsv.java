package org.manipulations.file;

import com.google.common.collect.ImmutableList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import org.objects.car.People;

public class ReadCsv {

  public static ImmutableList<People> getPeople() {
    List<People> peopleList = new ArrayList<>();

    String csvFile = "people.csv";
    ClassLoader cl = ReadCsv.class.getClassLoader();
    InputStream input = cl.getResourceAsStream(csvFile);

    try {
      assert input != null;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(input));
          CSVParser parser = new CSVParser(reader, CSVFormat.RFC4180)) {
        for (CSVRecord record : parser) {
          peopleList.add(new People(
              Integer.parseInt(record.get(0)),
              record.get(1),
              record.get(2),
              record.get(3),
              record.get(4),
              Integer.parseInt(record.get(5)),
              Boolean.parseBoolean(record.get(6)),
              record.get(7),
              record.get(8)));
        }

        return ImmutableList.copyOf(peopleList);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

