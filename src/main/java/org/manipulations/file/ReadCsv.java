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
import org.objects.people.People;

public class ReadCsv {

  public static ImmutableList<People> getPeople() {
    List<People> peopleList = new ArrayList<>();

    String csvFile = "people.csv";
    ClassLoader cl = ReadCsv.class.getClassLoader();
    InputStream input = cl.getResourceAsStream(csvFile);

    try {
      assert input != null;
      try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
        Iterable<CSVRecord> records = CSVFormat.RFC4180.builder()
            .setHeader()
            .setSkipHeaderRecord(false)
            .build().parse(reader);

        for (CSVRecord record : records) {
          peopleList.add(new People(
              Integer.parseInt(record.get("id")),
              record.get("firstName"),
              record.get("lastName"),
              record.get("eMail"),
              record.get("gender"),
              Integer.parseInt(record.get("age")),
              Boolean.parseBoolean(record.get("isMaried")),
              record.get("homeCountry"),
              record.get("homeCity")));
        }

        return ImmutableList.copyOf(peopleList);
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

