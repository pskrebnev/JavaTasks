package org.tasks.java21.assignment.solution;

public final class BusinessFaculty extends Faculty {

  public void business() { // custom BusinessFaculty method
    System.out.println("We teach accountancy, law, economics etc...");
  }

  @Override
  public String toString() {
    return "Business";
  }
}
