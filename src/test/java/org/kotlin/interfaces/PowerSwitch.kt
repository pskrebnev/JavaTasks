package org.kotlin.interfaces

import org.junit.jupiter.api.Test

sealed class State {
  object On : State()
  object Off : State()
}

class PowerSwitch {
  var state: State = State.Off
    private set

  fun powerTrigger() {
    state = when (state) {
      is State.On -> State.Off
      is State.Off -> State.On
    }
  }




}