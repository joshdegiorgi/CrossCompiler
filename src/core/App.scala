package core

import core.state.{StartState, StateManager}
import gui.UIUtilities
import gui.UIUtilities.loadParent
import io.{Code}
import javafx.stage.Popup
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.stage.Stage

object App extends JFXApp {

  // This should probably be moved, but for now it can live here
  private var codeInput = Code(None, None)
  def codeInput_:=(code: Code): Unit = codeInput = code
  def getCodeInput(): Code = codeInput



  StateManager.transition(StartState())

  def setStage(stage: PrimaryStage): Unit = {
    this.stage = stage
  }

  def getStage(): PrimaryStage = {
    this.stage
  }
}
