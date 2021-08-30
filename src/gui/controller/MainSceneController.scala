package gui.controller

import core.App
import core.state.{StartState, StateManager}
import gui.UIUtilities.{openFileChooser, openOperation, saveFileChooser}
import io.{CodeFile, IO}
import javafx.application.Platform
import javafx.scene.control.{MenuItem, TextArea}
import scalafxml.core.macros.sfxml

@sfxml
class MainSceneController(JavaTextArea: TextArea, PythonTextArea: TextArea, saveMenuItem: MenuItem) {

  initialize()

  //Is called each time any button is clicked
  def initialize(): Unit = {
    JavaTextArea.setStyle("-fx-font-family: monospace")
    setFormattedText(JavaTextArea, StateManager.getJavaCode())
    setSaveMenuItemStatus()
    setTitle()
  }

  // saves the current java code, resets the code, and transitions back
  def backOnClick(): Unit = {
    saveOnClick()
    StateManager.setJavaCode(CodeFile(None, None))
    StateManager.transition(StartState())
  }

  def saveOnClick(): Unit = {
    val text = Option(JavaTextArea.getText)
    val file = StateManager.getJavaCode().file
    IO.saveCodeToFile(CodeFile.withString(file, text))
  }

  def saveAsOnClick(): Unit = {
    saveFileChooser() match {
      case Some(file) =>
        val text = Option(JavaTextArea.getText)
        val code = CodeFile.withString(Some(file), text)
        StateManager.setJavaCode(code)
        IO.saveCodeToFile(code)
        forceBinding()
      case None => ()
    }
  }

  def openOnClick(): Unit = {
    openFileChooser().flatMap(file => openOperation(file)) match {
      case Some(code) => StateManager.setJavaCode(code)
      case None => ()
    }
    forceBinding()  // I really don't know why this is necessary, but it forces JFX to update
  }

  def closeOnClick(): Unit = {
    Platform.exit()
    System.exit(0)
  }

  def setFormattedText(textArea: TextArea, code: CodeFile): Unit = {
    code.raw match {
      case Some(list) =>
        textArea.clear()
        list.foreach(line => textArea.appendText(line + "\n"))
      case None => ()
    }
  }


  private def setSaveMenuItemStatus(): Unit = {
    val shouldEnable = StateManager.getJavaCode().file.isEmpty
    println(shouldEnable)
    saveMenuItem.setDisable(shouldEnable)
  }

  // TODO: BackOnClick not resetting the title
  private def setTitle(): Unit = {
    StateManager.getJavaCode().file match {
      case Some(file) => App.getStage().setTitle(file.getName)
      case None => ()
    }
  }

  private def forceBinding(): Unit = {
    JavaTextArea.setText(JavaTextArea.getText)
    PythonTextArea.setText(PythonTextArea.getText)
    StateManager.setJavaCode(StateManager.getJavaCode())
  }


}
