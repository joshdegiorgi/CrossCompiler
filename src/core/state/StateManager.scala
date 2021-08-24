package core.state

import core.App

object StateManager {

  private var currentState: State = _

  def state(): State = currentState

  def transition(state: State): Unit = {
    currentState = state
    App.setStage(state.stage)
  }
}
