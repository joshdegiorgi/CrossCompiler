package core.state
import gui.UIUtilities._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

case class StartState() extends State {

  override val stage: PrimaryStage = createStage(StartScene)(primaryStageBuilder)
  override def update(): Unit = ???
}
