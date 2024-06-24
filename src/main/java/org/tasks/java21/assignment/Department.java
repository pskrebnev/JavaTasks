package org.tasks.java21.assignment;

public abstract sealed class Department
    permits ComputerEngineeringDept
    , SoftwareEngineeringDept
    , AccountingDept {

}

final class ComputerEngineeringDept extends Department {

  public void compEng() {
    System.out.println("Custom computer engineering");
  }

  @Override
  public String toString() {
    return "Computer Engineering";
  }
}

final class SoftwareEngineeringDept extends Department {

  public void swEng() {
    System.out.println("Custom software engineering");
  }

  @Override
  public String toString() {
    return "Software Engineering";
  }
}

final class AccountingDept extends Department {

  public void accounting() {
    System.out.println("Custom accounting");
  }

  @Override
  public String toString() {
    return "Accounting";
  }
}
