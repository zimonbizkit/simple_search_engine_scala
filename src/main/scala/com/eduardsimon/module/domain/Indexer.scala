package com.eduardsimon.module.domain

import java.io.File

import scala.io.Source

object Indexer {

  val WORD = raw"(\w+)".r

  object InvertedIndex {
    import scala.io.Source

    def apply(contents: Map[String,List[String]]): Map[String, Set[String]] = {
      var i = Map[String, Set[String]]() withDefaultValue Set.empty
      contents.foreach {
        f => f._2 flatMap parse foreach {
          w => i = i + (w -> (i(w) + f._1))
        }
      }
      i
    }
  }

  def parse(string: String):Iterator[String] = WORD.findAllIn(string).map(_.toLowerCase)

  object FileReader {
    val okFileExtensions = List("txt")
    def getListOfFiles(dir: String, validExtensions: List[String]): Map[String ,List[String]] = {
      val d = new File(dir)
      if (d.exists && d.isDirectory) {
        d.listFiles.filter {
          file =>
            file.isFile &&
              validExtensions.exists(file.getName.endsWith(_))
        }.map {
          f => (f.getName , Source.fromFile(f).getLines().toList)
        }.toMap
      } else {
        Map[String ,List[String]]()
      }
    }
  }
}

