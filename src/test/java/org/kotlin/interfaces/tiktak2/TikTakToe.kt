package org.kotlin.interfaces.tiktak2

class TikTakToe {
  private val board = MutableList<Cell>(size = 9) { Cell.Empty }
  private var status: Status = Status.Idle
  private lateinit var player: Player
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



