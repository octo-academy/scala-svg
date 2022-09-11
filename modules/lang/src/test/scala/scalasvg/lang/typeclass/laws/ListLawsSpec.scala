package scalasvg.lang.typeclass.laws

import scalasvg.lang.std.BasicTypesEqual.given
import scalasvg.lang.std.ListInstances.given
import scalasvg.lang.typeclass.ContainerLikeLawsTest

class ListLawsSpec extends ContainerLikeLawsTest[List] {

  "List" should {
    behave.like(functor[String, Int, Float])
    behave.like(monad[Double, Byte])
    behave.like(monoid[Int])
    behave.like(apply[Long, Double, String])
    behave.like(applicative[Int, Byte, Float])
    behave.like(flatMap[String, Long, Byte])
  }

}
