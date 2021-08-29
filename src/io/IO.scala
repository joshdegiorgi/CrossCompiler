package io

import java.io.{BufferedWriter, File, FileWriter}
import java.net.URL
import scala.io.{BufferedSource, Source}
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

  def readFile(file: File): Try[List[String]] = {
    loadFile(file) match {
      case Success(source) =>
        val raw = source.getLines().toList
        source.close()
        Success(raw)
      case Failure(exception) =>
        println(s"Failed to Load ${file.getName}\n${exception.toString}")
        Failure(FileError("Failed to read file"))
    }
  }


  def saveCode(code: CodeFile): Try[Unit] = {
    if(code.file.isEmpty) Failure(IO.FileError("No File Defined!"))
    else if(code.raw.isEmpty) Failure(IO.FileError("No Lines Defined!"))
    else {
      val newFile = new File(code.file.get.getAbsolutePath)
      val writer = new BufferedWriter(new FileWriter(newFile))
      Try {
        for(line <- code.raw.get) {
          writer.write(line)
          writer.write("\n")
        }
        writer.close()
      }
    }

  }

}
