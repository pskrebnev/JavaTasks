package org.patterns.build;

public class BuilderPatternDemo {
  public static void main(String[] args) {
    // Example 1: Creating a Person with all optional parameters
    Person person1 = new Person.PersonBuilder("John", "Doe")
        .age(30)
        .email("john.doe@example.com")
        .phoneNumber("555-1234")
        .address("123 Main St, City, State")
        .build();

    System.out.println("Person 1: " + person1);

    // Example 2: Creating a Person with only some optional parameters
    Person person2 = new Person.PersonBuilder("Jane", "Smith")
        .age(25)
        .email("jane.smith@example.com")
        .build();

    System.out.println("Person 2: " + person2);

    // Example 3: Creating a Person with only required parameters
    Person person3 = new Person.PersonBuilder("Bob", "Johnson")
        .build();

    System.out.println("Person 3: " + person3);

    // Example 4: Method chaining can be done in any order
    Person person4 = new Person.PersonBuilder("Alice", "Wilson")
        .phoneNumber("555-5678")
        .address("456 Oak Ave, Town, State")
        .age(28)
        .email("alice.wilson@example.com")
        .build();

    System.out.println("Person 4: " + person4);
  }
}

