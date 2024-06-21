package org.tasks.java21.assignment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class University {

  public static void main(String[] args) {
    seqColl();
    seqSet();
    seqMap();

    LecturerRecord alan = createRecord(
        "Alan Austin"
        , 64
        , new BusinessFaculty()
        , new AccountingDept());
  }

  private static LecturerRecord createRecord(
      String name
      , Integer age
      , Faculty faclt
      , Department dept) {

    return new LecturerRecord(
        name
        , age
        , faclt
        , dept);
  }

  private static void seqColl() {
    List<String> names = new ArrayList<>();
    names.add("John");
    names.add("Jane");
    names.add("Bob");
    System.out.println("Sequential Collection: " + names);
  }

  private static void seqSet() {
    Set<String> uniqueNames = new HashSet<>();
    uniqueNames.add("John");
    uniqueNames.add("Jane");
    uniqueNames.add("Bob");
    uniqueNames.add("John");
    System.out.println("Sequential Set: " + uniqueNames);
  }

  private static void seqMap() {
    Map<String, Integer> ageMap = new LinkedHashMap<>();
    ageMap.put("John", 30);
    ageMap.put("Jane", 25);
    ageMap.put("Bob", 35);
    System.out.println("Sequential Map: " + ageMap);
  }
}
