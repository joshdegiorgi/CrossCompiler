package gui.controller

import core.App
import core.state.{StartState, StateManager}
import gui.UIUtilities
import gui.UIUtilities.{createStage, dialogStageBuilder, loadFileDialog}
import javafx.application.Platform
import scalafxml.core.macros.sfxml

@sfxml
class MainSceneController {

  def backOnClick(): Unit = {
    StateManager.transition(StartState())
  }

  def loadOnClick(): Unit = {
    val test = loadFileDialog(App.getStage())
    println(test)
  }

  def closeOnClick(): Unit = {
    Platform.exit()
    System.exit(0)
  }
}
