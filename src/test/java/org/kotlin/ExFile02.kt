package org.kotlin

fun main() {
  val person = Person()
  println(person.age)
  person.age = 10
  println(person.age)
}

class Person {
  var age = 0
    get() {
      return field
    }
    set(value) {
      field = value
    }
}

