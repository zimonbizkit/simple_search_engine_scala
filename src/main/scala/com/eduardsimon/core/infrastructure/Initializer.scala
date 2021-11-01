package com.eduardsimon.core.infrastructure

import com.eduardsimon.core.module.integration.application.ApplicationService

object Initializer extends App{

  val exitCodes = List(":q", ":quit")
  val prompt = new Prompter

  checkParameters

  val dataPath = args(0)
  println(s"Importing values from files found in ${dataPath}")

  val indexerService = ApplicationService.IndexData(dataPath)

  var query = prompt.readFromPrompt

  while(exitCodes.indexOf(query) == -1) {
    if (!query.isEmpty) {
      val results = ApplicationService.QueryEngine(query)
      printResults(results)
    }

    query = prompt.readFromPrompt
  }

  System.exit(0)

  private def checkParameters = {
    if (args.isEmpty) {
      println("Folder parameter has not been passed. Exiting.")
      System.exit(1)
    }
  }
  private def printResults(results:Map[String,Double] ) =
    for((k,v) <- results.take(10)) println(s"File :${k}: ${v}%")


}
