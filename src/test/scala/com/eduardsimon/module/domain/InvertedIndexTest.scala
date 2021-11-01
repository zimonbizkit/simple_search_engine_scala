package com.eduardsimon.module.domain

import java.io.{File, PrintWriter}
import java.nio.file.{Files, Paths}

import org.scalatest.flatspec.AnyFlatSpec

class InvertedIndexTest extends AnyFlatSpec {
  "Inverted Index" should "classify the contents of files in a map" in {
    val fileContent = "Hello hello hello how are you "
    val fileNames = List("file1.txt" , "file2.txt" , "file3.txt")
    val fileList = fileNames.map{

    }
  }
}
