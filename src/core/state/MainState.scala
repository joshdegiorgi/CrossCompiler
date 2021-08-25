package core.state
import gui.UIUtilities.{MainScene, createStage, primaryStageBuilder}
import scalafx.application.JFXApp.PrimaryStage

case class MainState() extends State {
  override val stage: PrimaryStage = createStage(MainScene)(primaryStageBuilder)

  override def update(): Unit = ???
}
