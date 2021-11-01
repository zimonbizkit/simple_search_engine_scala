package com.eduardsimon.core.module.unit.domain

import org.scalatest.flatspec.AnyFlatSpec

class TokenizerTest extends AnyFlatSpec {
  "Tokenizer" should " parse the same way as the indexer parses words" in {
    assert(
      Tokenizer.tokenize("HEY! WASSAP??? How, are you? 🤪") ==
        List("hey","wassap","how","are","you")
    )
  }

  "Tokenizer" should "collapse repeated words from input" in {
    assert(
      Tokenizer.tokenize("HEY! WASSAP??? How, are you? 🤪toma toma") ==
        List("hey","wassap","how","are","you","toma")
    )
  }
}
