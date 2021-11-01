package com.eduardsimon.core.module.domain

import com.eduardsimon.core.module.domain.Indexer.FileReader
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.must.Matchers.{be, noException}


class FileReaderTest extends AnyFlatSpec {
  val DATA_FOLDER="data/"
  "FileReader" should "read the filesystem for _valid_ files and return File objects " in {
    val listOfFiles = FileReader.readFiles(DATA_FOLDER,Indexer.okFileExtensions)
    assert(listOfFiles.map(f => f._1) == List("file1.txt","horatio.txt"))
  }

  "FileReader" should "be decoupled from file parsing" in {
    val listOfFiles = FileReader.readFiles(DATA_FOLDER,List("pdf"))
    assert(listOfFiles.map(f => f._1) == List("horatiopdf.pdf"))
  }

  "FileReader" should "not fail when no files could be parsed" in {
    val listOfFiles = FileReader.readFiles(DATA_FOLDER,List("parquet"))
    //assert(listOfFiles.isEmpty)
    noException should be thrownBy FileReader.readFiles(DATA_FOLDER,List("parquet"))
  }

}