package org.kotlin.interfaces.tiktak2

import kotlin.system.exitProcess

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
      checkTheBoard()
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

  private fun checkTheBoard() {
    val winningCombinations = listOf(
      listOf(0, 1, 2),
      listOf(3, 4, 5),
      listOf(6, 7, 8),
      listOf(0, 3, 6),
      listOf(1, 4, 7),
      listOf(2, 5, 8),
      listOf(0, 4, 8),
      listOf(2, 4, 6)
    )

    val player1Cells = mutableListOf<Int>()
    val player2Cells = mutableListOf<Int>()
    board.forEachIndexed { index, cell ->
      if (cell.placeholder == 'X')
        player1Cells.add(element = index)
      if (cell.placeholder == 'O')
        player2Cells.add(element = index)
    }

    println("Your moves: $player1Cells")
    println("Computer moves: $player2Cells")

    run CombinationLoop@{
      winningCombinations.forEach { combination ->
        if (player1Cells.containsAll(elements = combination)) {
          won()
          return@CombinationLoop
        }
        if (player2Cells.containsAll(elements = combination)) {
          lost()
          return@CombinationLoop
        }
      }
    }

    if (board.none { it is Cell.Empty } && status is Status.Running) {
      draw()
    }
    if (status is Status.GameOver) {
      finish()
      playAgain()
    }
  }

  private fun finish() {
    status = Status.Idle
    board.replaceAll { Cell.Empty }
  }

  private fun playAgain() {
    print("Do you want to play another game? (Y/N): ")
    val input = readlnOrNull()
    try {
      require(value = input != null)
      val capitalizedInput = input.replaceFirstChar(Char::titlecase)
      val positive = capitalizedInput.contains(other = "Y")
      val negative = capitalizedInput.contains(other = "N")
      require(value = positive || negative)
      if (positive)
        start()
      else if (negative)
        exitProcess(status = 0)
    } catch (e: IllegalArgumentException) {
      println("Wrong option. Try either 'Y' or 'N'.")
      playAgain()
    }
  }

  private fun won() {
    status = Status.GameOver
    printBoard()
    println("My congrats! ${player.name}, you won")
  }

  private fun lost() {
    status = Status.GameOver
    printBoard()
    println("Hey, ${player.name}. You lost")
  }

  private fun draw() {
    status = Status.GameOver
    printBoard()
    println("The draw :(")
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



