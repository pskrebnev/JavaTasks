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

    while (status is Status.Running) {
      getCell()
    }
  }

  private fun getName() {
    print("Enter your name: ")
    val name = readlnOrNull()
    try {
      require(value = name != null)
      player = Player(name = name, symbol = 'X')
      println("It's your move, $name")
      printBoard()
    } catch (e: Throwable) {
      println("Invalid name")
    }
  }

  private fun getCell() {
    val input = readlnOrNull()
    try {
      require(value = input != null)
      val cellNumber = input.toInt()
      require(value = cellNumber in 0..8)
      setCell(cellNumber = cellNumber)
    } catch (e: Throwable) {
      println("Invalid number")
    }
  }

  private fun setCell(cellNumber: Int) {
    val cell = board[cellNumber]
    if (cell is Cell.Empty) {
      board.set(
        index = cellNumber,
        element = Cell.Filled(player = player)
      )
      generateComputerMove()
      printBoard()
    } else {
      println("Cell already taken, choose another")
    }
  }

  private fun generateComputerMove() {
    try {
      val availableCells = mutableListOf<Int>()
      board.forEachIndexed { index, cell ->
        if (cell is Cell.Empty) availableCells.add(element = index)
      }
      if (availableCells.isNotEmpty()) {
        val randomCell = availableCells.random()
        board.set(
          index = randomCell,
          element = Cell.Filled(player = Player())
        )
      }
    } catch (e: Throwable) {
      println("Error: ${e.message}")
    }
  }

  private fun printBoard() {
    println()
    println("+-----+-----+-----+")
    println("|  ${board[0].placeholder}  |  ${board[1].placeholder}  |  ${board[2].placeholder}  |")
    println("+-----+-----+-----+")
    println("|  ${board[3].placeholder}  |  ${board[4].placeholder}  |  ${board[5].placeholder}  |")
    println("+-----+-----+-----+")
    println("|  ${board[6].placeholder}  |  ${board[7].placeholder}  |  ${board[8].placeholder}  |")
    println("+-----+-----+-----+")
    println()
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



