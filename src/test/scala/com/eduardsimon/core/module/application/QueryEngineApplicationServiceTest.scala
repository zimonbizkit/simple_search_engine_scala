package com.eduardsimon.core.module.application

import org.scalatest.flatspec.AnyFlatSpec

class QueryEngineApplicationServiceTest extends AnyFlatSpec {
  "QueryEngine" should "retrieve empty map if no coincidences were found" in {
    ApplicationService.invertedIndex = Map(
      "hola" -> Set("file1.txt","file2.txt"),
      "que" -> Set("file1.txt"),
      "tal" -> Set("file1.txt"))

    assert(ApplicationService.QueryEngine("query") == Map.empty)
  }

  "QueryEngine" should "retrieve a map of files and coincidence percentage if there are matches" in {
    ApplicationService.invertedIndex = Map(
      "hola" -> Set("file1.txt","file2.txt"),
      "que" -> Set("file1.txt"),
      "tal" -> Set("file1.txt"))

    assert(
      ApplicationService.QueryEngine("hola")
        == Map(
        "file2.txt" -> 100.0,
        "file1.txt" -> 100.0
      )
    )
  }
}
