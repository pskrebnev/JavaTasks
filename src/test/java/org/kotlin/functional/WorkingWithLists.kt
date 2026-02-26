package org.kotlin.functional

class WorkingWithLists {
}

fun main() {
  val first = listOf("First", "Second", "Third", "Fifth")
  val second = listOf(1, 5, 2, 9, 4, 6)

  println(first.zip(second))
  println(second.zip(first))
  println(second zip first )
}