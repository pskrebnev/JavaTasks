package org.smalltasks.objects;

public class Product {

  String category;
  double price;

  public Product(String category, double price) {
    this.category = category;
    this.price = price;
  }

  public String getCategory() {
    return category;
  }

  public double getPrice() {
    return price;
  }
}
