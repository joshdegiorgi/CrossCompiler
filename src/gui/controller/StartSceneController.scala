package gui.controller

import core.state.StateManager
import core.state.MainState
import scalafxml.core.macros.sfxml

@sfxml
class StartSceneController {

  def enterOnClick(): Unit = {
    StateManager.transition(MainState())
  }

}
