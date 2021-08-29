package core.state
import global.ResourcePaths.MainScene
import gui.UIUtilities._
import io.CodeFile
import scalafx.application.JFXApp.PrimaryStage

case class MainState(rawInput: CodeFile = CodeFile(None, None)) extends State {
  // This should probably be moved, but for now it can live here
  def setRawInput(input: CodeFile): MainState = this.copy(rawInput = input)
  override val stage: PrimaryStage = createStage(MainScene)(primaryStageBuilder)

}
