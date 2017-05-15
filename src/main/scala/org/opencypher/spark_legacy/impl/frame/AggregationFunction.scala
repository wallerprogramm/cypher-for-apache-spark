package org.opencypher.spark_legacy.impl.frame

import org.opencypher.spark_legacy.impl.StdFrameSignature
import org.opencypher.spark.api.types._
import org.opencypher.spark.api.value.{CypherInteger, CypherList, CypherValue}

sealed trait AggregationFunction {
  def inField: Symbol
  def outField: Symbol
  def outType(sig: StdFrameSignature): CypherType

  def unit: CypherValue
}

case class Count(inField: Symbol)(val outField: Symbol) extends AggregationFunction {
  override def outType(sig: StdFrameSignature): CypherType = CTInteger
  override def unit = CypherInteger(0)
}

case class Collect(inField: Symbol)(val outField: Symbol) extends AggregationFunction {
  override def outType(sig: StdFrameSignature): CypherType = CTList(sig.field(inField).get.cypherType)
  override def unit = CypherList.empty
}
