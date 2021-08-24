package gui

import global.ResourcePaths.FX_Layouts
import javafx.scene.{Parent, Scene}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene
import scalafxml.core.{FXMLView, NoDependencyResolver}

import scala.util.{Failure, Success, Try}

object Utilities {

  val StartScene: String = "start_scene"
  val MainScene: String = "main_scene"

  def constructScenePath(title: String): String = {
    s"${FX_Layouts}/${title}.fxml"
  }

  private def loadScene(title: String): Try[Scene] = Try {
    val resource = getClass.getResource(constructScenePath(title))
    val root: Parent = FXMLView(resource, NoDependencyResolver)
    new Scene(root)
  }

  private def buildStage(attempt: Try[Scene]): PrimaryStage = {
    attempt match {
      case Success(s) => new PrimaryStage {
        scene = new scalafx.scene.Scene(s)
      }
      case Failure(exception) =>
        println(exception.toString)
        new PrimaryStage()
    }
  }

  val loadAndBuild: String => PrimaryStage = loadScene _ andThen buildStage
}
