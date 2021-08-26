package gui.controller

import core.App
import core.state.{MainState, StateManager}
import gui.UIUtilities
import gui.UIUtilities._
import io.{Code, IO}
import javafx.stage.FileChooser
import scalafxml.core.macros.sfxml

@sfxml
class StartSceneController {


  def enterOnClick(): Unit = {
    StateManager.transition(MainState())
  }

  def popupOnClick(): Unit = {
    val chosenFile = loadFileDialog(App.getStage())
    val lines = chosenFile.flatMap(file => IO.readFile(file))
    App.codeInput_:=(Code(chosenFile, lines))
  }


}
