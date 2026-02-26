package org.kotlin.functional

fun main() {
  println(list1)
  println(list2)

  val (match, noMatch) = list1.partition { number ->
    number > 6
  }

  println(match)
  println(noMatch)
}

val list1 = List(size = 10) { it }
val list2 = MutableList(size = 10) { it }

