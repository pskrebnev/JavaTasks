package org.kotlin

data class User(val name: String, val age: Int)

fun main() {
//  val user1 = User(name = "Dalian", age = 25)
//  val user2 = User(name = "Dalian", age = 25)
//  println(user1.equals(user2)) // true
//  println(user1 == user2) // true
//  println(user1 === user2) // false
  elvisCheck()
}

fun checkNull() {
//  var posibNl: String = null // error
  var posibNl1: String? = null // ok
}

fun elvisCheck() {
  getName(name = "Stefan") // 6
  getName(name = null) // 0
}

fun getName(name: String?) {
  println(name?.length ?: 0)
}




