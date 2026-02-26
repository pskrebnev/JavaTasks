package org.kotlin.interfaces

import kotlin.test.*

class SampleTest {
  private val actualNumber = 30

  @Test
  fun testTheNumber() {
    assertEquals(
      expected = 30,
      actual = actualNumber,
      message = "Incorrect Number,"
    )
  }

  @Test
  fun `calculate function, pass zero, assert error`() {
    assertFails {
      calculate(num1 = 100, num2 = 0)
    }
  }

  @Test
  fun `calculate function, pass correct value, assert result`() {
    assertIs<Int>(value = calculate(num1 = 100, num2 = 10), message = "Error!")
  }
}

fun calculate(num1: Int, num2: Int) = num1 / num2
