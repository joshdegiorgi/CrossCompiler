package core

import core.state.{StartState, StateManager}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object App extends JFXApp {

  StateManager.transition(StartState())

  def setStage(stage: PrimaryStage): Unit = {
    this.stage = stage
  }

}
