package com.eduardsimon.core.infrastructure

import scala.io.StdIn

final class Prompter {
  val promptMessage = "search>"
   def readFromPrompt : String = {
    print(promptMessage)
    StdIn.readLine()
  }
}
