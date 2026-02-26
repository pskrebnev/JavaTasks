package org.kotlin.interfaces

class PersonOne(val name: String) {
  init {
    println("Hey, ${name}! I'm initializing...")
  }
}

class PersonSecond(name: String) {
  init {
    println("Hey, ${name}! I'm initializing...")
  }
}

fun main() {
  val p1 = PersonOne(name = "First")
  val p2 = PersonSecond(name = "Second")

  println("Name1 = ${p1.name}")
//  println("Name2 = ${p2.name}")


}











