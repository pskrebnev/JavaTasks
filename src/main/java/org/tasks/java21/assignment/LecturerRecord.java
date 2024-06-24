package org.tasks.java21.assignment;

public record LecturerRecord(
    String name
    , Integer age
    , Faculty faculty
    , Department dept) {

  public LecturerRecord {
    if (name.isBlank() || age < 0) {
      String errorMsg = """
          Illegal argument passed:
                 "name": %s,
                 "age": %s
          """.formatted(name, age);

      throw new IllegalArgumentException("\n" + errorMsg);
    }
  }

  @Override
  public String toString() {
    return """
        Name is %s
        Age is %d
        Faculty is %s
        Department is %s""".formatted(name, age, faculty, dept);
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
      case AccountingDept acc -> {
        System.out.println("Dept of: " + acc);
        acc.accounting();
      }
      default -> throw new IllegalArgumentException("Invalid department: " + dept);
    }
  }
}