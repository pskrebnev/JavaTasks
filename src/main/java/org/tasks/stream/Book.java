package org.tasks.stream;

class Book {

  private final String title;
  private final double price;

  Book(String title, double price) {
    this.title = title;
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public double getPrice() {
    return price;
  }

  public String toString() {
    return title + " " + price;
  }
}