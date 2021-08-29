package gui.controller

import core.App
import core.state.{StartState, StateManager}
import gui.UIUtilities.{handleFileDialog, loadFileChooser}
import io.{CodeFile, IO}
import javafx.application.Platform
import javafx.scene.control.TextArea
import scalafxml.core.macros.sfxml

import scala.reflect.runtime.universe.Try
import scala.util.{Failure, Success}

@sfxml
class MainSceneController(JavaTextArea: TextArea, PythonTextArea: TextArea) {

  initialize()

  //Is called each time any button is clicked
  def initialize(): Unit = {
    JavaTextArea.setStyle("-fx-font-family: monospace")
    setFormattedText(JavaTextArea, StateManager.getJavaCode())
  }

  // saves the current java code, resets the code, and transitions back
  def backOnClick(): Unit = {
    saveOnClick()
    StateManager.setJavaCode(CodeFile(None, None))
    StateManager.transition(StartState())
  }

  // TODO: Implement support for splitting JavaTextArea.getText into List[String] by "\n"
  // gets the code from the text area, gets the associated file, and saves it
  def saveOnClick(): Unit = {
    val text = Option(JavaTextArea.getText)
    println(text)
    val file = StateManager.getJavaCode().file
    IO.saveCode(CodeFile.withString(file, text))
  }

  def loadOnClick(): Unit = {
    handleFileDialog() match {
      case Some(code) =>
        StateManager.setJavaCode(code)
      case None => ()
    }
    StateManager.setJavaCode(StateManager.getJavaCode())  // I really don't know why this is necessary, but it forces JFX to update
  }

  def closeOnClick(): Unit = {
    Platform.exit()
    System.exit(0)
  }

  def setFormattedText(textArea: TextArea, code: CodeFile): Unit = {
    code.raw match {
      case Some(list) =>
        textArea.clear()
        for(line <- list) {
          textArea.appendText(line)
          textArea.appendText("\n")
        }
      case None => ()
    }
  }
}
