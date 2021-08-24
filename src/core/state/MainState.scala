package core.state
import gui.Utilities.{MainScene, loadAndBuild}
import scalafx.application.JFXApp.PrimaryStage

case class MainState() extends State {
  override val stage: PrimaryStage = loadAndBuild(MainScene)

  override def update(): Unit = ???
}
