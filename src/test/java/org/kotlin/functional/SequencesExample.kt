package org.kotlin.functional

class SequencesExample {
}

fun main() {
  val list = listOf(1, 2, 3, 4, 5)
  list.forEach customName@{
    if (it == 3) {
      return@customName
    }
    println("Loop $it")
  }
  println("Completed")
}

