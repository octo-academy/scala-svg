package scalasvg.lang.typeclass.behavior

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import scalasvg.lang.laws.{FlatMapLaws, IsEqual}
import scalasvg.lang.typeclass.{FlatMap, Equal}

trait FlatMapBehavior[F[_]] {
  self: AnyWordSpec with Matchers with Checkers =>
  
  def flatMap[A, B, C](using Arbitrary[F[A]], Arbitrary[A => F[B]], Arbitrary[B => F[C]], Equal[F[C]], FlatMap[F]): Unit = {
    val flatMapLaws = FlatMapLaws[F]
    
    "follow all FlatMap laws," which afterWord("include") {
      "associativity" in {
        check {
          forAll { (fa: F[A], f: A => F[B], g: B => F[C]) =>
            flatMapLaws.flatMapAssociativity(fa, f, g).isEqual
          }
        }
      }
    }
  }
}
