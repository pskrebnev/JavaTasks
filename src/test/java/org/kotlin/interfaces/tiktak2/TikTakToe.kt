package org.kotlin.interfaces.tiktak2

class TikTakToe {
  private val board = MutableList<Cell>(size = 9) { Cell.Empty }
  private var status: Status = Status.Idle
  private lateinit var player: Player

  fun start() {
    status = Status.Running

    println("+-----------------------------+")
    println("| Welcome to Tic-Tac-Toe game |")
    println("|Pick a number from '0' to '8'|")
    println("+-----------------------------+")
    getName()
  }

  private fun getName() {
    print("Enter your name: ")
    val name = readlnOrNull()
    try {
      require(value = name != null)
      player = Player(name = name, symbol = 'X')
      println("It's your move $name")
    } catch (e: Throwable) {
      println("Invalid name")
    }
  }
}

data class Player(
  val name: String = "Comp",
  val symbol: Char = 'O'
)

sealed class Status {
  object Idle : Status()
  object Running : Status()
  object GameOver : Status()
}

sealed class Cell(val placeholder: Char) {
  object Empty : Cell(placeholder = '_')
  data class Filled(val player: Player) : Cell(placeholder = player.symbol)
}



