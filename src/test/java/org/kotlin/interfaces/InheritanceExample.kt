package org.kotlin.interfaces

class InheritanceExample {
}


class Parent {
  fun getFood() {
    println("Getting some food.")
  }
}

class Child(private val parent: Parent) {
  fun getFood() {
    parent.getFood()
  }
}

fun main() {
  Singleton.printName()
}

object Singleton {
  fun printName() {
    println("Hi, my name is Stefan.")
  }
}

