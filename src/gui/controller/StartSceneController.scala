package gui.controller

import core.App
import core.state.{MainState, StateManager}
import gui.UIUtilities._
import io.{CodeFile, IO}
import scalafxml.core.macros.sfxml

@sfxml
class StartSceneController {


  def enterOnClick(): Unit = {
    StateManager.transition(MainState())
  }

  def popupOnClick(): Unit = {
//    val chosenFile = loadFileChooser()
//    val lines = chosenFile.flatMap(file => IO.readFile(file))
//    StateManager.setJavaCode(CodeFile(chosenFile, lines))
  }


}
