package com.eduardsimon.core.module.domain


import com.eduardsimon.core.module.domain.Indexer.InvertedIndex
import org.scalatest.flatspec.AnyFlatSpec

class InvertedIndexTest extends AnyFlatSpec {
  "Inverted Index" should "classify the contents of classified files in a map" in {

    val chunkedContent = Map(
      "file1.txt" ->List("hola que tal","como estamos","esto son tres lineas"),
      "file2.txt" ->List("esto mola","cuando funciona","si no pues mal"),
    )
    val invertedIndex = InvertedIndex(chunkedContent)

    assert(invertedIndex.get("que").contains(Set("file1.txt")))
    assert(invertedIndex.get("mola").contains(Set("file2.txt")))
  }

  "Inverted Index" should "retrieve an empty set of results if no coincidences were found" in {

    val chunkedContent = Map(
      "file1.txt" ->List("hola que tal","como estamos","esto son tres lineas"),
      "file2.txt" ->List("esto mola","cuando funciona","si no pues mal"),
    )
    val invertedIndex = InvertedIndex(chunkedContent)

    assert(!invertedIndex.contains("buen"))
  }
}
