package com.eduardsimon.core.module.domain

object Tokenizer {
  val SEPARATOR = " "

  def tokenize(string:String): List[String] = Indexer
    .parse(string)
    .mkString(SEPARATOR)
    .split(SEPARATOR)
    .toList
    .distinct
}
