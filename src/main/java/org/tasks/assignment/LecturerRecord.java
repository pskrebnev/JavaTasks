package org.tasks.assignment;

public final class LecturerRecord {

  private final String name;
  private final Integer age;
  private final Faculty faculty;
  private final Department dept;

  @Override
  public String toString() {
    return """
        Name is %s
        Age is %d
        Faculty is %s
        Department is %s""".formatted(name, age, faculty, dept);
  }

  public LecturerRecord(
      String name
      , Integer age
      , Faculty faculty
      , Department dept) {
    if (name.isBlank() || age < 0) {
      throw new IllegalArgumentException(buildCustomMessage(name, age));
    }
    this.name = name;
    this.age = age;
    this.faculty = faculty;
    this.dept = dept;
  }

  public boolean hasPhd() {
    String prefix = name.substring(0, Math.min(name.length(), 3));
    String suffix = name.substring(Math.max(name.length() - 3, 0));

    return switch (prefix) {
      case "Dr." -> true;
      default -> switch (suffix) {
        case "PhD" -> true;
        default -> false;
      };
    };
  }

  public void whichFaculty() {
    switch (faculty) {
      case EngineeringFaculty eng -> {
        System.out.println("Faculty of: " + eng);
        eng.engineering();
      }
      case HumanitiesFaculty hum -> {
        System.out.println("Faculty of: " + hum);
        hum.humanities();
      }
      case BusinessFaculty bus -> {
        System.out.println("Faculty of: " + bus);
        bus.business();
      }
      default -> throw new IllegalArgumentException("Invalid faculty: " + faculty);
    }
  }

  public void whichDept() {
    switch (dept) {
      case ComputerEngineeringDept compEng -> {
        System.out.println("Dept of: " + compEng);
        compEng.compEng();
      }
      case SoftwareEngineeringDept swEng -> {
        System.out.println("Dept of: " + swEng);
        swEng.swEng();
      }
      case SocialCareDept socialCare -> {
        System.out.println("Dept of: " + socialCare);
        socialCare.socialCare();
      }
      case AccountingDept acc -> {
        System.out.println("Dept of: " + acc);
        acc.accounting();
      }
      default -> throw new IllegalArgumentException("Invalid department: " + dept);
    }
  }

  private static String buildCustomMessage(String name, Integer age) {
    return """
        Illegal argument passed:
        "name": %s,
        "age": %s
        """.formatted(name, age);
  }
}
