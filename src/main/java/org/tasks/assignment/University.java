package org.tasks.assignment;

public class University {

  public static void main(String[] args) {

    try {
      LecturerRecord lecturerWithBlankName = new LecturerRecord(
          ""
          , 22
          , null
          , null);

      System.out.println(lecturerWithBlankName);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }

    try {
      LecturerRecord lecturerWithInvalidAge = new LecturerRecord(
          "Joe Bloggs"
          , -3
          , null
          , null);
    } catch (IllegalArgumentException e) {
      System.out.println(e);
    }
  }
}
