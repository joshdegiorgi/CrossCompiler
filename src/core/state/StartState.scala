package core.state
import global.ResourcePaths.StartScene
import gui.UIUtilities._
import scalafx.application.JFXApp.PrimaryStage

case class StartState() extends State {
  override val stage: PrimaryStage = createStage(StartScene)(primaryStageBuilder)
}
