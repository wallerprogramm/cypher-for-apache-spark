package org.opencypher.spark.legacy.impl.frame

import org.opencypher.spark.legacy.api.frame.BinaryRepresentation
import org.opencypher.spark.prototype.api.types.CTNode

class RowAsProductTest extends StdFrameTestSuite {

  test("RowAsProduct converts rows to products") {
    val a = add(newNode.withLabels("A").withProperties("name" -> "Zippie"))
    val b = add(newNode.withLabels("B").withProperties("name" -> "Yggie"))

    new GraphTest {

      import frames._

      val result = allNodes('n).asRow.asProduct.testResult

      result.signature shouldHaveFields ('n -> CTNode)
      result.signature shouldHaveFieldSlots ('n -> BinaryRepresentation)
      result.toSet should equal(Set(a, b).map(Tuple1(_)))
    }
  }
}