package org.kotlin

fun main() {
  var cl1 = Example()
  var cl2 = Example()

  println("${cl1} and ${cl2}")
}

class Example

class Person1 (val name: String) {
  fun getName() {
    println(name)
  }

  override fun equals(other: Any?): Boolean {
    return super.equals(other)
  }

  override fun toString(): String {
    return name
  }
}
