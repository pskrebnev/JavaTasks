package org.objects.car;

public class Car {

  private int id;
  private String producer;
  private String model;
  private int year;
  private String color;
  private double price;

  public Car(int id
      , String producer
      , String model
      , int year
      , String color
      , double price) {
    this.id = id;
    this.producer = producer;
    this.model = model;
    this.year = year;
    this.color = color;
    this.price = price;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public String toString() {
    return "Car{" +
        "id=" + id +
        ", producer='" + producer + '\'' +
        ", model='" + model + '\'' +
        ", year=" + year +
        ", color='" + color + '\'' +
        ", price=" + price +
        '}';
  }

}
