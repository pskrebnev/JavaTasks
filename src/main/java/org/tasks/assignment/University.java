package org.tasks.assignment;

public class University {

  public static void main(String[] args) {
    tryWithException();
    System.out.println(createJane());
    createJane().whichFaculty();
    createJane().whichDept();
    System.out.println(createJane().hasPhd());
    System.out.println(createAnne());
    System.out.println(createAnne().hasPhd() ? "Anne has a PhD" : null);
    System.out.println(createJoe());
    System.out.println(createJoe().hasPhd() ? "Joe has a PhD" : null);
  }

  private static LecturerRecord createJane() {
    return new LecturerRecord(
        "Jane Bloggs"
        , 24
        , new EngineeringFaculty()
        , new SoftwareEngineeringDept()
    );
  }

  private static LecturerRecord createAnne() {
    Faculty businessFaculty = new BusinessFaculty();
    Department accountingDept = new AccountingDept();
    LecturerRecord anneBloggs = new LecturerRecord("Dr. Anne Bloggs"
        , 35
        , businessFaculty
        , accountingDept);

    return anneBloggs;
  }

  private static LecturerRecord createJoe() {
    Faculty humanitiesFaculty = new HumanitiesFaculty();
    Department socialDept = new SocialCareDept();
    LecturerRecord joe = new LecturerRecord("Joe Bloggs PhD"
        , 54
        , humanitiesFaculty
        , socialDept);

    return joe;
  }

  private static void tryWithException() {
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
