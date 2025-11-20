package org.patterns.build;

public class Person {
  // Required parameters
  private final String firstName;
  private final String lastName;

  // Optional parameters
  private final int age;
  private final String email;
  private final String phoneNumber;
  private final String address;

  // Private constructor - only Builder can create instances
  private Person(PersonBuilder builder) {
    this.firstName = builder.firstName;
    this.lastName = builder.lastName;
    this.age = builder.age;
    this.email = builder.email;
    this.phoneNumber = builder.phoneNumber;
    this.address = builder.address;
  }

  // Getters
  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public int getAge() {
    return age;
  }

  public String getEmail() {
    return email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public String getAddress() {
    return address;
  }

  @Override
  public String toString() {
    return "Person{" +
        "firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", age=" + age +
        ", email='" + email + '\'' +
        ", phoneNumber='" + phoneNumber + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

  // Static nested Builder class
  public static class PersonBuilder {
    // Required parameters
    private final String firstName;
    private final String lastName;

    // Optional parameters - initialized to default values
    private int age = 0;
    private String email = "";
    private String phoneNumber = "";
    private String address = "";

    // Constructor with required parameters
    public PersonBuilder(String firstName, String lastName) {
      this.firstName = firstName;
      this.lastName = lastName;
    }

    // Methods for optional parameters - return this for method chaining
    public PersonBuilder age(int age) {
      this.age = age;
      return this;
    }

    public PersonBuilder email(String email) {
      this.email = email;
      return this;
    }

    public PersonBuilder phoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
      return this;
    }

    public PersonBuilder address(String address) {
      this.address = address;
      return this;
    }

    // Build method to create Person instance
    public Person build() {
      return new Person(this);
    }
  }
}