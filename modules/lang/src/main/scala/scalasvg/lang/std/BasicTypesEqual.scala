package scalasvg.lang.std

import scalasvg.lang.typeclass.Equal

trait BasicTypesEqual {

  given Equal[Int] with {
    def equal(x: Int, y: Int): Boolean = x == y
  }

  given Equal[String] with {
    def equal(x: String, y: String): Boolean = x == y
  }

  given Equal[Byte] with {
    def equal(x: Byte, y: Byte): Boolean = x == y
  }

  given Equal[Double] with {
    def equal(x: Double, y: Double): Boolean = x == y
  }

  given Equal[Float] with {
    def equal(x: Float, y: Float): Boolean = x == y
  }

  given Equal[Long] with {
    def equal(x: Long, y: Long): Boolean = x == y
  }

}

object BasicTypesEqual extends BasicTypesEqual
