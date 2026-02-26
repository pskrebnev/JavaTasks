package org.kotlin.functional

class FunctionalProgramming {}

fun main() {
//  button {
//    println("Clicked")
//  }

//  button(onClick = { println("Clicked more...") })

  println(mapI)
  println(mapS)
}

val listStr = listOf("first", "second", "third")
val listInt = listOf(3, 6, 23, 10)

fun <T> toIndexedMap(list: List<T>): Map<Int, T> =
  list.mapIndexed { index, value -> index + 1 to value }.toMap()

val mapI = toIndexedMap(listInt)
val mapS = toIndexedMap(listStr)

fun button(onClick: () -> Unit) {
  onClick()
}