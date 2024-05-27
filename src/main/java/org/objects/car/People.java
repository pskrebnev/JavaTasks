package org.objects.car;

public class People {

  public People(int id
      , String firstName
      , String lastName
      , String eMail
      , String gender
      , int age
      , boolean isMaried
      , String homeCountry
      , String homeCity) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.eMail = eMail;
    this.gender = gender;
    this.age = age;
    this.isMaried = isMaried;
    this.homeCountry = homeCountry;
    this.homeCity = homeCity;
  }

  @Override
  public String toString() {
    return "People{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", eMail='" + eMail + '\'' +
        ", gender='" + gender + '\'' +
        ", age=" + age +
        ", isMaried=" + isMaried +
        ", homeCountry='" + homeCountry + '\'' +
        ", homeCity='" + homeCity + '\'' +
        '}';
  }

  private int id;
  private String firstName;
  private String lastName;
  private String eMail;
  private String gender;
  private int age;
  private boolean isMaried;
  private String homeCountry;
  private String homeCity;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return eMail;
  }

  public void setEmail(String eMail) {
    this.eMail = eMail;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public boolean isMaried() {
    return isMaried;
  }

  public void setMaried(boolean maried) {
    isMaried = maried;
  }

  public String getHomeCountry() {
    return homeCountry;
  }

  public void setHomeCountry(String homeCountry) {
    this.homeCountry = homeCountry;
  }

  public String getHomeCity() {
    return homeCity;
  }

  public void setHomeCity(String homeCity) {
    this.homeCity = homeCity;
  }

}
