package core

import core.state.{StartState, StateManager}
import gui.UIUtilities
import gui.UIUtilities.loadParent
import javafx.stage.Popup
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.stage.Stage

object App extends JFXApp {

  StateManager.transition(StartState())

  val popup = new Popup
  val parent = loadParent(UIUtilities.Popup).get
  popup.getContent.add(parent)

  def setStage(stage: PrimaryStage): Unit = {
    this.stage = stage
  }

  def getStage(): PrimaryStage = {
    this.stage
  }
}
