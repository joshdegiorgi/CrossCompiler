package core.state

import core.App
import io.CodeFile
import parse.TranslationUnit

object StateManager {

  private var currentState: State = _

  def state(): State = currentState

  def transition(state: State): Unit = {
    currentState = state
    App.setStage(state.stage)
  }

  // TODO: Refactor the matching logic
  def setJavaCode(code: CodeFile): Unit = {
    currentState match {
      case s: MainState =>
        val s2 = s.setRawInput(code)
        transition(s2)
      case _ => ()
    }
  }

  def getJavaCode(): CodeFile = {
    currentState match {
      case s: MainState => s.rawInput
      case _ => CodeFile(None, None)
    }
  }

  def setPythonCode(code: CodeFile): Unit = {
    currentState match {
      case s: MainState =>
        val s2 = s.setRawOutput(code)
        transition(s2)
      case _ => ()
    }
  }

  def getPythonCode(): CodeFile = {
    currentState match {
      case s: MainState => s.rawOutput
      case _ => CodeFile(None, None)
    }
  }

  def translate(input: String): Unit = {
    currentState match {
      case s: MainState =>
        TranslationUnit.walk(input)
        val output = TranslationUnit.print()
        val rawInput =  s.rawInput.setRaw(input)
        val rawOutput = s.rawOutput.setRaw(output)
        val s2 = MainState(rawInput, rawOutput)
        transition(s2)
      case _ => ()
    }
  }


}
