package org.udemy.lab01;

class Person {

  private Integer age;
  private String name;

  @Override
  public String toString() {
    return "Person{" +
        "age=" + age +
        ", name='" + name + '\'' +
        ", height=" + height +
        '}';
  }

  private Double height;

  Person(String name, Integer age, Double height) {
    this.name = name;
    this.age = age;
    this.height = height;
  }

  Integer getAge() {
    return age;
  }

  String getName() {
    return name;
  }

  Double getHeight() {
    return height;
  }
}
