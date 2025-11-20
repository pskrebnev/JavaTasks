package org.smalltasks

import org.smalltasks.objects.Employee
import org.smalltasks.objects.Order
import org.smalltasks.objects.Product

fun main() {
  // for task 1
  val products = listOf(
    Product("Electronics", 999.99),
    Product("Electronics", 1499.99),
    Product("Books", 19.99),
    Product("Books", 29.99),
    Product("Books", 39.99),
    Product("Clothing", 49.99),
    Product("Clothing", 79.99),
    Product("Electronics", 299.99)
  )

  // for task 2
  val employees = listOf(
    Employee("IT", "Alice"),
    Employee("HR", "Bob"),
    Employee("IT", "Charlie"),
    Employee("Sales", "Diana"),
    Employee("IT", "Eve"),
    Employee("HR", "Frank"),
    Employee("Sales", "Grace"),
    Employee("Sales", "Henry")
  )

  // for task 3
  val orders = listOf(
    Order("John", 150.50),
    Order("Sarah", 200.00),
    Order("John", 75.25),
    Order("Mike", 300.00),
    Order("Sarah", 50.00),
    Order("John", 100.00),
    Order("Mike", 120.75)
  )

  val str = "Modify the given Java code to combine the astronaut's name and year of birth using" +
    " Java's Stream API instead of using string concatenation with a loop. You should implement" +
    " a solution that separates the data by commas and then reconstructs the astronaut's" +
    " information using string joining techniques. Ensure your code works for a variable number" +
    " of astronaut fields, should future data include more information such as a year of death."

//  calcAveragePrice(products).forEach { (k, v) -> println("$k -> $v") }
//  countByDepartment(employees).forEach { (k, v) -> println("$k -> $v") }
//  countSpentByCustomer(orders).forEach { (k, v) -> println("$k = $v") }
//  countWords(str).forEach { (k, v) -> println("$k -> $v") }
//  countChars(str).forEach { (k, v) -> println("$k -> $v") }
  countWords1(str).forEach { (k,v) -> println("$k -> $v") }
//  println(countSubstr(str, "su"))
}

private fun countSubstr(str: String, subStr: String): Int {
  return str.windowed(subStr.length)
    .count { it == subStr }
}

// Task 1
private fun calcAveragePrice(products: List<Product>): Map<String, Double> {
  return products.groupBy { it.category }
    .mapValues { (_, list) -> list.map { it.price }.average() }
    .toList()
    .sortedBy { (_, value) -> value }.reversed()
    .toMap(LinkedHashMap())
}

// Task 2
private fun countByDepartment(employees: List<Employee>): Map<String, Long> {
//  return employees.groupBy { it.department }
//    .mapValues { it.value.size.toLong() }
//    .toList()
//    .sortedBy { (_, value) -> value }
//    .toMap(LinkedHashMap())
  return employees.groupingBy { it.department }
    .eachCount()
    .entries
    .sortedBy { (_, value) -> value }.reversed()
    .associate { it.key to it.value.toLong() }
}

// Task 3
private fun countSpentByCustomer(orders: List<Order>): Map<String, Double> {
  return orders.groupBy { it.customer }
    .mapValues { it.value.sumOf { it.amount } }
    .toList()
    .sortedBy { (_, value) -> value }.reversed()
    .toMap(LinkedHashMap())
}

private fun countWords(st: String): Map<String, Long> {
  return st.split("[\\s.,:?]+".toRegex())
    .filter { it.isNotBlank() }
    .groupingBy { it.lowercase() }
    .eachCount()
    .entries
    .sortedBy { it.value }.reversed()
    .filter { it.value > 2 }
    .associate { it.key to it.value.toLong() }
}

private fun countWords1(str: String): Map<String, Int> {
  return str.split("[\\s.,:?]+".toRegex())
    .filter { it.isNotBlank() }
    .groupingBy { it.lowercase() }
    .eachCount()
    .entries
    .filter { it.value > 2 }
    .sortedBy { it.value }
    .associate { it.key to it.value }
}

private fun countApp(str: String, subStr: String): Int {
  return str.windowed(subStr.length)
    .count { it == subStr }
}






private fun countChars(str: String): Map<Char, Int> {
  return str.groupingBy { it.lowercaseChar() }
    .eachCount()
    .entries
    .filter { it.key.isLetterOrDigit() }
    .sortedBy { it.value }
    .associate { it.key to it.value  }
}

private fun countSubstring(str: String, subStr: String): Int {
  return str.windowed(subStr.length)
    .count { it == subStr }
}



