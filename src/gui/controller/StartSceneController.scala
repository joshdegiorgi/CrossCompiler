package gui.controller

import core.App
import core.state.StateManager
import core.state.MainState
import gui.UIUtilities
import gui.UIUtilities._
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.layout.StackPane
import javafx.stage.{Modality, Popup, Stage}
import scalafx.scene.control.Dialog
import scalafxml.core.macros.sfxml

@sfxml
class StartSceneController {


  def enterOnClick(): Unit = {
    StateManager.transition(MainState())
  }

  def popupOnClick(): Unit = {
    val dialog = createStage(UIUtilities.Popup)(dialogStageBuilder)
    dialog.showAndWait()
  }


}
