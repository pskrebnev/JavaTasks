package org.kotlin.interfaces

import kotlin.test.*

class PowerSwitchTest {
  private var powerSwitch = PowerSwitch()

  @Test
  fun `assert init power state after triggering state`() {
    assertEquals(
      expected = State.Off,
      actual = powerSwitch.state,
      message = "Init state should be Off!"
    )

    powerSwitch.powerTrigger()

    assertEquals(
      expected = State.On,
      actual = powerSwitch.state,
      message = "Init state should be On!"
    )
  }
}