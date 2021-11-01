package com.eduardsimon.core.module.integration.application

import com.eduardsimon.core.module.unit.domain.{Indexer, Ranker, Tokenizer}

import scala.util.{Success, Try}

object ApplicationService {
  var invertedIndex : Map[String, Set[String]] = Map()
  var fileContents : Map[String ,List[String]] = Map()
  object IndexData {
    def apply(folder:String): Unit = {
      fileContents = Indexer.FileReader.readFiles(folder,Indexer.okFileExtensions)
      invertedIndex = Indexer.InvertedIndex(fileContents)
    }
  }

  object QueryEngine {
    val SEPARATOR = " "
    def apply(query: String): Map[String,Double] = {
      val tokenizedQuery = Tokenizer.tokenize(query)
      Try {
        tokenizedQuery.map(invertedIndex)
      } match {
        case Success (partialMatchScan) => Ranker.rank(
          tokenizedQuery,
          partialMatchScan,
          fileContents.keys.toList
        )
        case _ => Map[String,Double]()
      }
    }
  }
}
