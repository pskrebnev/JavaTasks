package org.kotlin.interfaces

fun interface Person {
  fun eyeColor(): String
}

class Man : Person {
  override fun eyeColor(): String {
    return "blue"
  }
}





