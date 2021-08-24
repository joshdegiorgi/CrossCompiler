package gui.controller

import core.state.{StartState, StateManager}
import scalafxml.core.macros.sfxml

@sfxml
class MainSceneController {

  def backOnClick(): Unit = {
    StateManager.transition(StartState())
  }

}
