package org.kotlin

fun main() {
//  val nums = listOf(1, 2, 3, 4, 5)
//  val nnums = setOf(1,5,1)
//  nums.forEach { println(it) }
//  println("---")
//  nnums.forEach { println(it) }

  val map1 = mapOf(
    "Monkey" to "brown",
    "Cat" to "yellow",
    "Dog" to "black"
  )

//  println("${map1.keys} ${map1.values}")
  map1.forEach { (k, v) -> println("$k = $v") }

//  val monkey = Animal(name = "Monkey", age = 2)
//  val tiger = Animal(name = "Tiger", age = 4)
//  val whale = Animal(name = "Whale", age = 6)
//  val animalList = listOf(monkey, tiger, whale)
//  val animalMap = animalList.associateBy({ it.name}, { it.age })
//  println(animalMap)
  condPrint("first")
  condPrint("random")
  chosePrior()
}

class Animal(
  val name: String,
  val age: Int
)

fun condPrint(name: String) = when (name) {
  "first" -> println("Print first")
  "second" -> println("Print second")
  else -> println("Unknown")
}

//fun condPrint1(name: String): Any {
//  return when (name) {
//    "first" -> println("Print first")
//    "second" -> println("Print second")
//    else -> println("Unknown")
//  }
//}
sealed class PersonThis {
  data object Male : PersonThis()
  data object Female : PersonThis()
}

enum class Priority(val color: String) {
  LOW(color = "Red"), MEDIUM(color = "Blue"), HIGH(color = "Green");

  val number: Int
    get() = when (this) {
      LOW -> 1
      MEDIUM -> 2
      HIGH -> 3
    }
}

fun chosePrior() {
  val pr = Priority.LOW
  println(Priority.MEDIUM.color) // Blue

  for (priority in Priority.entries) {
    println(priority.color)
  }
}
