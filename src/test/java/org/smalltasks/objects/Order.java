package org.smalltasks.objects;

public class Order {

  String customer;
  double amount;

  public Order(String customer, double amount) {
    this.customer = customer;
    this.amount = amount;
  }

  public String getCustomer() {
    return customer;
  }

  public double getAmount() {
    return amount;
  }
}
