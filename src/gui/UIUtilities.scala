package gui

import global.ResourcePaths.FX_Layouts
import io.IO
import javafx.scene.{Parent, Scene}
import javafx.stage.{FileChooser, Modality, Stage, Window}
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene
import scalafxml.core.{FXMLView, NoDependencyResolver}

import java.io.File
import java.net.URL
import scala.util.{Failure, Success, Try}

object UIUtilities {

  val StartScene: String = "start_scene"
  val MainScene: String = "main_scene"

  def constructFXMLPath(title: String): String = {
    s"${FX_Layouts}/${title}.fxml"
  }

  // Given a title of an FXMl, will attempt to load the FXML as a URL
  def loadFXML(title: String): Try[URL] = Try {
    val pathToResource = constructFXMLPath(title)
    IO.loadResource(pathToResource)
  }

  // Given a title of a FXML, will attempt to load the corresponding Scene from disk
  def loadScene(title: String): Try[Scene] = {
    loadParent(title).flatMap(parent => Try(new Scene(parent)))
  }

  // Given a title of an FXML, will attempt to load the FXML as a parent from disk
  def loadParent(title: String): Try[Parent] = {
    loadFXML(title).flatMap(url => Try(FXMLView(url, NoDependencyResolver)))
  }


  // Takes a sceneTitle, and a function that builds a type of stage from that scene Title
  def createStage[S](sceneTitle: String)(builder: Scene => S): S = {
    loadScene(sceneTitle) match {
      case Success(scene: Scene) => builder(scene)
      case Failure(exception) =>
        println(s"Stage Creation Failed! Attempting to Recover with Null Scene! \n${exception.toString}")
        builder(null)
    }
  }

  val primaryStageBuilder = (s: Scene) => new PrimaryStage { scene = new scalafx.scene.Scene(s)}
  val normalStageBuilder = (s: Scene) => new Stage { setScene(s) }
  //Unused for now, but can be used to load popups
  val dialogStageBuilder = (s: Scene) => new Stage {
    setTitle("Load File")
    setScene(s)
    initModality(Modality.APPLICATION_MODAL)
  }

  def loadFileDialog(window: Window): Option[File] = {
    val fileChoose = new FileChooser()
    Option(fileChoose.showOpenDialog(window))
  }


}
