package gui.controller

import core.state.{MainState, StateManager}
import scalafxml.core.macros.sfxml

@sfxml
class StartSceneController {


  def enterOnClick(): Unit = {
    StateManager.transition(MainState())
  }

}
