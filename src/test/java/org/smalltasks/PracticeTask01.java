package org.smalltasks;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.smalltasks.objects.Employee;
import org.smalltasks.objects.Order;
import org.smalltasks.objects.Product;

public class PracticeTask01 {

  public static void main(String[] args) {
    String st = "Returns a string representation of the object.";
    // for task 1
    List<Product> products = List.of(
        new Product("Electronics", 999.99),
        new Product("Electronics", 1499.99),
        new Product("Books", 19.99),
        new Product("Books", 29.99),
        new Product("Books", 39.99),
        new Product("Clothing", 49.99),
        new Product("Clothing", 79.99),
        new Product("Electronics", 299.99)
    );

    // for task 2
    List<Employee> employees = List.of(
        new Employee("IT", "Alice"),
        new Employee("HR", "Bob"),
        new Employee("IT", "Charlie"),
        new Employee("Sales", "Diana"),
        new Employee("IT", "Eve"),
        new Employee("HR", "Frank"),
        new Employee("Sales", "Grace"),
        new Employee("Sales", "Henry")
    );

    // for task 3
    List<Order> orders = List.of(
        new Order("John", 150.50),
        new Order("Sarah", 200.00),
        new Order("John", 75.25),
        new Order("Mike", 300.00),
        new Order("Sarah", 50.00),
        new Order("John", 100.00),
        new Order("Mike", 120.75)
    );

    calcAveragePrice(products).forEach((k, v) -> System.out.println(k + "->" + v));
    countByDepartment(employees).forEach((k, v) -> System.out.println(k + " -> " + v));
    countSpentByCustomer(orders).forEach((k, v) -> System.out.println(k + " = " + v));
  }

  //  Task 1: Group products by category and calculate the average price in each category.
  // Expected: Map<String, Double>
  private static Map<String, Double> calcAveragePrice(List<Product> products) {
    return products.stream()
        .collect(Collectors.groupingBy(
            Product::getCategory,
            Collectors.averagingDouble(Product::getPrice)
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByValue())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (oldV, newV) -> oldV,
            LinkedHashMap::new
        ));
  }

  //  Task 2: Group employees by department and count how many are in each department.
  // Expected: Map<String, Long>
  private static Map<String, Long> countByDepartment(List<Employee> empl) {
    return empl.stream()
        .collect(Collectors.groupingBy(
            Employee::getDepartment,
            Collectors.counting()
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (ol, n) -> ol,
            LinkedHashMap::new
        ));
  }

  //  Task 3: Group orders by customer and find the total amount spent by each customer.
  // Expected: Map<String, Double>
  private static Map<String, Double> countSpentByCustomer(List<Order> orders) {
    return orders.stream()
        .collect(Collectors.groupingBy(
            Order::getCustomer,
            Collectors.summingDouble(Order::getAmount)
        ))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            Map.Entry::getValue,
            (o, n) -> o,
            LinkedHashMap::new
        ));
  }
}
