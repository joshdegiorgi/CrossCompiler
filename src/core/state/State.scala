package core.state

import scalafx.application.JFXApp.PrimaryStage

trait State {
  val stage: PrimaryStage
  def update(): Unit
}
