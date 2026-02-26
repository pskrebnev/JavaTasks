package org.kotlin

fun main() {
  val car1 = Car(prop = Tires(size = 15))
  val car2 = Car(prop = "17")
  val car3 = Car(prop = 18)

  println(car1.getValue())
  println(car2.getValue())
  println(car3.getValue())
}

// Generic implementation
data class Tires(val size: Int)
class Car<T>(private val prop: T) {
  fun getValue(): T = prop
}

