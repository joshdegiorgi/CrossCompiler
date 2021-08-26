package gui.controller

import core.App
import core.state.{StartState, StateManager}
import gui.UIUtilities
import gui.UIUtilities.{createStage, dialogStageBuilder, loadFileDialog}
import io.{Code, IO}
import javafx.application.Platform
import javafx.fxml.FXML
import javafx.scene.control.TextArea
import scalafxml.core.macros.sfxml

@sfxml
class MainSceneController(JavaTextArea: TextArea, PythonTextArea: TextArea) {

  initialize()

  def initialize(): Unit = {
    JavaTextArea.setText(App.getCodeInput().collapseLinesForOutput())
  }

  def backOnClick(): Unit = {
    saveOnClick() // Autosave
    App.codeInput_:=(Code(None, None))  // reset the current code
    StateManager.transition(StartState())
  }

  def saveOnClick(): Unit = {
    val newLines = Option(JavaTextArea.getText.split("\n").toList)
    val file = App.getCodeInput().file
    IO.saveCode(Code(file, newLines))
  }

  def loadOnClick(): Unit = {
    val chosenFile = loadFileDialog(App.getStage())
    val lines = chosenFile.flatMap(file => IO.readFile(file))
    App.codeInput_:=(Code(chosenFile, lines))
    JavaTextArea.setText(App.getCodeInput().collapseLinesForOutput())
  }

  def closeOnClick(): Unit = {
    Platform.exit()
    System.exit(0)
  }
}
