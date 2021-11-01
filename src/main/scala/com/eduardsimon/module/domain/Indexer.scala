package com.eduardsimon.module.domain

import java.io.File

object Indexer {

  val WORD = raw"(\w+)".r

  object InvertedIndex {
    import scala.io.Source

    def apply(files: List[File]): Map[String, Set[File]] = {
      var i = Map[String, Set[File]]() withDefaultValue Set.empty
      files.foreach {
        f => Source.fromFile(f).getLines flatMap parse foreach {
          w => i = i + (w -> (i(w) + f))
        }
      }
      i
    }
  }

  def parse(string: String):Iterator[String] = WORD.findAllIn(string).map(_.toLowerCase)

  object FileReader {
    val okFileExtensions = List("txt")
    def getListOfFiles(dir: String, validExtensions: List[String]): List[File] = {
      val d = new File(dir)
      if (d.exists && d.isDirectory) {
        d.listFiles.filter {
          file =>
            file.isFile &&
              validExtensions.exists(file.getName.endsWith(_))
        }.toList
      } else {
        List[File]()
      }
    }
  }
}

