package org.kotlin

fun main() {
//  val name = "Stefan"
//  println("${name.h1()} has length ${name.count()}")

  val person1 = Person2("John", 25, "Belgrade, Serbia")
  println("${person1.info()} ${person1.addressInfo(26)}")
}

fun String.h2(): String {
  return "<h2>${this}</h2>"
}

fun String.count(): Int {
  return this.length
}

fun String.h1(): String {
  return "<h1>$this</h1>"
}

class Person2(
  private val name: String,
  private val age: Int,
  val address: String
) {
  fun info() = "My name is $name, and I'm $age years old."
}

fun Person2.addressInfo(number: Int): String =
  "Come visit me in ${this.address} and my flat is ${number}"

