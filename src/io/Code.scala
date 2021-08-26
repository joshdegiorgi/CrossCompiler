package io

import core.App.codeInput

import java.io.{BufferedWriter, File, FileWriter}
import scala.util.{Failure, Try}

case class Code(file: Option[File], lines: Option[List[String]]) {
  def setLines(lines: Option[List[String]]): Code = {
    this.copy(lines = lines)
  }

  def collapseLinesForOutput(): String = {
    lines match {
      case Some(lines) =>
        val addedNewLine = lines.map(line => line.concat("\n"))
        addedNewLine.mkString
      case None => ""
    }
  }


}
