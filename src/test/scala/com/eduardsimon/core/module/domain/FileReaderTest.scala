package com.eduardsimon.core.module.domain

import Indexer.FileReader
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.{be, noException}

class FileReaderTest extends AnyFlatSpec {
  val DATA_FOLDER="data/"
  "FileReader" should "read the filesystem for _valid_ files and return File objects " in {
    val listOfFiles = FileReader.readFiles(DATA_FOLDER,Indexer.okFileExtensions)
    assert(
      listOfFiles.keys ==
        Set("file1.txt",
          "shepherd.txt",
          "TheCompleteWorksOfWilliamShakespeare.txt",
          "horatio.txt"
        )
    )
  }

  "FileReader" should "not fail when no files could be parsed" in {
    val listOfFiles = FileReader.readFiles(DATA_FOLDER,List("parquet"))
    //assert(listOfFiles.isEmpty)
    noException should be thrownBy FileReader.readFiles(DATA_FOLDER,List("parquet"))
  }

}