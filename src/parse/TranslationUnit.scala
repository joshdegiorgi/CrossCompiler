package parse

import org.antlr.v4.runtime.CharStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTreeWalker
import parse.antlr.{Java8Lexer, Java8Parser}

import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

// acts as a container for the ANTLR logics
// this is likely very inefficient but it works for now
object TranslationUnit {

  private var currentScope: Int = 0

  private def stringToCharStream(str: String): CharStream = {
    val stream = new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8))
    CharStreams.fromStream(stream)
  }

  // this is probably inefficient?
  def walk(input: String): Unit = {
    val lexer = new Java8Lexer(stringToCharStream(input))
    val tokens = new CommonTokenStream(lexer)
    val parser = new Java8Parser(tokens)
    val tree = parser.compilationUnit()
    val walker = new ParseTreeWalker()
    val listener = new ParserListener(parser)
    walker.walk(listener, tree)
  }

  // broke
  def enterScope(): Unit = currentScope += 1
  def exitScope(): Unit = currentScope -= 1
  def tabs(): String = List.fill(currentScope)("\t").mkString
  def prepended(str: String): String = tabs() + str
  def outputNoTab(str: String) = print(str)
  def outputWithTab(str: String): Unit = print(prepended(str))
}


