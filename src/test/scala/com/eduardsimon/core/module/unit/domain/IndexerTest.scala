package com.eduardsimon.core.module.unit.domain

import org.scalatest.flatspec.AnyFlatSpec

class IndexerTest extends AnyFlatSpec {
  "The parser " should " get only english trimmed words as indicated by regex metacharacter" in {
    val cleanableString ="""
      | potato "·$%&/%$·
      |       carrot
      |
      |
      |
      | cabbage "Hola     ,que  tal?!!"· "
      |""".stripMargin

    assert(Indexer.parse(cleanableString).toList == List("potato","carrot","cabbage","hola","que","tal"))
  }

  "The parser " should " not crash if there are no valid words" in {
    val impossibleString ="""
       ·$%&/%$· 😀 🚀$·%&/()&()/&&/()/()=/*^¨¨:
       |""".stripMargin

    assert(Indexer.parse(impossibleString).toList.isEmpty)
  }
  "The parser " should "lowercase the words " in {
    assert(Indexer.parse("HEY WHATS UP?!").toList == List("hey","whats","up"))
  }
}
