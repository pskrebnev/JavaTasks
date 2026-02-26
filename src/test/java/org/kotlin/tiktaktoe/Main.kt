package org.kotlin.tiktaktoe

fun main() {

  while (!checkWin()) {
    // draw table and info
    drawTable()
    println("You play for `$signX`.")
    println("Computer plays for `$signO`.")

    // get symbol from the console
    var mark = signEmpty

    // human step
    while (!checkMark(mark)) {
      println("Choose a free cell. The cell shouldn't be already occupied. ")
      print("Enter just one free cell from `0` to `8`: ")
      mark = readln() // Reads the entire line

      if (checkMark(mark) && listMarks[mark.toInt()] == signEmpty) {
        updateMark(mark.toInt(), signX)
        freecell.removeAt(mark.toInt())
        humanSteps.add(mark.toInt())
      }

      // check for win
      if (checkWin(humanSteps, winSet)) {
        println("You win!!!")
        victory = true
      }
    }

    if (victory) break


    // TBD for comp
  }
}

var victory = false
val signX = "X"
val signO = "O"
val signEmpty = " "

// symbols for table
val horizontalBar = "+---+---+---+"
val beginCell = "| "
val middleCell = " | "
val endCell = " |"

// list of free cells
var freecell = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8)

// steps from human
var humanSteps = mutableSetOf<Int>()

// steps from the computer
var computerSteps = mutableSetOf<Int>()

// values for table
var listMarks = mutableListOf(
  signEmpty,
  signEmpty,
  signEmpty,
  signEmpty,
  signEmpty,
  signEmpty,
  signEmpty,
  signEmpty,
  signEmpty
)

// substitute element by index
fun updateMark(index: Int, newMark: String) {
  if (index in listMarks.indices) {
    listMarks[index] = newMark
  }
}

// check for win
fun checkWin(setSteps: Set<Int>, setWins: Set<Set<Int>>): Boolean {
  for (partialSet: Set<Int> in setWins) {
    if (setSteps.containsAll(partialSet)) return true
  }
  return false
}

// win set
// 012, 345, 678
// 036, 147, 258
// 048, 246
val winSet = setOf(
  setOf(0, 1, 2),
  setOf(3, 4, 5),
  setOf(6, 7, 8),
  setOf(0, 3, 6),
  setOf(1, 4, 7),
  setOf(2, 5, 8),
  setOf(0, 4, 8),
  setOf(2, 4, 6)
)

fun checkWin(): Boolean {
  return false
}

fun checkMark(mark: String): Boolean {
  return mark.matches(Regex("[0-8]"))
}


fun drawTable() {
  println(horizontalBar)
  // row #1
  print(beginCell)
  print(listMarks[0])
  print(middleCell)
  print(listMarks[1])
  print(middleCell)
  print(listMarks[2])
  println(endCell)

  println(horizontalBar)
  // row #2
  print(beginCell)
  print(listMarks[3])
  print(middleCell)
  print(listMarks[4])
  print(middleCell)
  print(listMarks[5])
  println(endCell)

  println(horizontalBar)
  // row #3
  print(beginCell)
  print(listMarks[6])
  print(middleCell)
  print(listMarks[7])
  print(middleCell)
  print(listMarks[8])
  println(endCell)

  println(horizontalBar)
}


