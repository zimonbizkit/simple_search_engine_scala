package com.eduardsimon.core.module.domain

object Ranker {
  // FIXME please lets review this
  val RANKER_PRECISION= 2
  def rank(tokenizedQuery: List[String],partialMatchScan:List[Set[String]]) : Map[String,Double] = {
    if (partialMatchScan.isEmpty) {
      Map[String,Double]()
    }
    tokenizedQuery.zip(partialMatchScan)
      .flatten{case(k, vs) => vs.map((_, k))}
      .groupBy(_._1)
      .mapValues{_.map(_._2)}
      .toList
      .sortBy(v => v._2.size).reverse
      .toMap
      .map { case (k,v) => (k, 100* setPrecision(v.size.toDouble/tokenizedQuery.size.toDouble))}
  }
  private def setPrecision(rankScore:Double):Double =
    BigDecimal(rankScore)
      .setScale(RANKER_PRECISION, BigDecimal.RoundingMode.HALF_DOWN)
      .toDouble

}
