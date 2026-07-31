package org.kotlin.functional

fun main() {
  println(list1)
//  println(list2)
//  println(list3)
  println(list4)
  println(list5)

  val (match, noMatch) = list1.partition { number ->
    number > 6
  }

  println(match)
  println(noMatch)
}

val list1 = List(size = 10) { it }
val list2 = MutableList(size = 10) { it }
val list3 = (0..9).toMutableList()
val list4 = ('a'..'f').toList()
val list5 = MutableList(size = 26) { 'a' + it }



