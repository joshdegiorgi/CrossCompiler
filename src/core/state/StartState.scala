package core.state
import gui.Utilities._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

case class StartState() extends State {

  override val stage: PrimaryStage = loadAndBuild(StartScene)
  override def update(): Unit = ???
}
