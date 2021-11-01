package com.eduardsimon.core.module.domain

import org.scalatest.flatspec.AnyFlatSpec

class RankerTest extends AnyFlatSpec {

  val fileList = List("file1.txt","file2.txt","file3.txt")
  "Ranker" should "order results according coincidence percentage across files" in {
    val tokenizedQuery   = List("hola","que","tal")
    val partialMatchScan = List(
      Set("file1.txt","file2.txt","file3.txt"),
      Set("file1.txt"),
      Set("file1.txt")
    )
    val result = Ranker.rank(tokenizedQuery,partialMatchScan,fileList)
    assert(
      result == Map(
        "file1.txt" -> 100.0,
        "file3.txt" -> 33.0,
        "file2.txt" -> 33.0,
      )
    )
  }

  "Ranker" should " result even percentages if coincidences are scattered across files" in {
    val tokenizedQuery   = List("hola","que","tal")
    val partialMatchScan = List(
      Set("file1.txt","file2.txt","file3.txt"),
      Set("file2.txt","file3.txt"),
      Set("file1.txt")
    )
    val result = Ranker.rank(tokenizedQuery,partialMatchScan,fileList)
    assert(
      result == Map(
        "file1.txt" -> 67.0,
        "file2.txt" -> 67.0,
    "file3.txt" -> 67.0,
      )
    )
  }

  "Ranker" should "not process anything if there are no partial matches" in {
    val tokenizedQuery   = List("hola","que","tal")
    val partialMatchScan = List.empty

    val result = Ranker.rank(tokenizedQuery,partialMatchScan,fileList)

    assert(result == Map.empty)
  }

  "Ranker" should " return coincidence percentage with one decimal" in {
    val tokenizedQuery   = List("hola","que","tal")
    val partialMatchScan = List(
      Set("file1.txt")
    )
    val result = Ranker.rank(tokenizedQuery, partialMatchScan,fileList).values.toList(0).toString
    assert(
      result.split("\\.")(1).length == 1
    )
  }
}
