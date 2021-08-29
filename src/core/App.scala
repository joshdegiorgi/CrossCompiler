package core

import core.state.{StartState, StateManager}
import io.CodeFile
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object App extends JFXApp {



  StateManager.transition(StartState())

  def setStage(stage: PrimaryStage): Unit = {
    this.stage = stage
  }

  def getStage(): PrimaryStage = {
    this.stage
  }

  def forceRefresh(): Unit = {
    stage.getScene.getWindow.setWidth(stage.getScene.getWindow.getWidth + .001)
  }
}
