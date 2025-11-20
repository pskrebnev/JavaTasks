package org.smalltasks.objects;

public class Employee {

  String department;
  String name;

  public Employee(String department, String name) {
    this.department = department;
    this.name = name;
  }

  public String getDepartment() {
    return department;
  }

  public String getName() {
    return name;
  }
}
