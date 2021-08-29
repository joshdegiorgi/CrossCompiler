package core.state

import scalafx.application.JFXApp.PrimaryStage


// Primarily functions as a container for JFX Stage
trait State {
  val stage: PrimaryStage
}
