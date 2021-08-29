package io

import java.io.File

case class CodeFile(file: Option[File], raw: Option[List[String]]) {
  def setLines(lines: Option[List[String]]): CodeFile = {
    this.copy(raw = lines)
  }

  def asString(): String = {
    raw match {
      case Some(lines) => lines.mkString
      case None => ""
    }
  }
}
object CodeFile {
  def withString(file: Option[File], asString: Option[String]): CodeFile = {
    val lines = asString.map(string => string.split("\n").toList)
    CodeFile(file, lines)
  }
}
