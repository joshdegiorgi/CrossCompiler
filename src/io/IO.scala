package io

import java.net.URL
import scala.io.{BufferedSource, Source}
import java.io.{BufferedWriter, File, FileWriter}
import scala.util.{Failure, Success, Try}


object IO {

  case class FileError(msg: String = "") extends Throwable {
    override def toString: String = s"IO.FileError\n$msg"
  }

  def loadResource(path: String): URL = {
    getClass.getResource(path)
  }

  private def verifyFileType(file: File): Try[File] = {
    if(file.getName.takeRight(4).equals("java")) Success(file)
    else Failure(FileError("Wrong File Extension"))
  }

  private def loadFile(file: File): Try[BufferedSource] = {
    verifyFileType(file).flatMap(file => Try(Source.fromFile(file)))
  }

  def readFile(file: File): Option[List[String]] = {
    loadFile(file) match {
      case Success(source) =>
        val lines = source.getLines().toList
        Some(lines)
      case Failure(exception) =>
        println(s"Failed to Load ${file.getName}\n${exception.toString}")
        None
    }
  }

  def saveCode(code: Code): Try[Unit] = {
    if(code.file.isEmpty) Failure(IO.FileError("No File Defined!"))
    if(code.lines.isEmpty) Failure(IO.FileError("No Lines Defined!"))
    else {
      val newFile = new File(code.file.get.getName)
      val writer = new BufferedWriter(new FileWriter(newFile))
      Try {
        for(line <- code.lines.get) writer.write(line)
        writer.close()
      }
    }

  }

}
