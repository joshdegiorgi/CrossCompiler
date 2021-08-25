package gui.controller

import core.App
import core.state.{MainState, StateManager}
import gui.UIUtilities
import gui.UIUtilities._
import javafx.stage.FileChooser
import scalafxml.core.macros.sfxml

@sfxml
class StartSceneController {


  def enterOnClick(): Unit = {
    StateManager.transition(MainState())
  }

  def popupOnClick(): Unit = {
    val test = loadFileDialog(App.getStage())
    println(test)
  }


}
