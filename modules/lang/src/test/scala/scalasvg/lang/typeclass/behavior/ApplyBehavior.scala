package scalasvg.lang.typeclass.behavior

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import scalasvg.lang.laws.{IsEqual, ApplyLaws}
import scalasvg.lang.typeclass.{Equal, Apply}

trait ApplyBehavior[F[_]] {
  self: AnyWordSpec with Matchers with Checkers =>
  
  def apply[A, B, C](using Arbitrary[F[A]], Arbitrary[F[A => B]], Arbitrary[F[B => C]], Equal[F[C]], Apply[F]): Unit = {
    val applyLaws = ApplyLaws[F]
    
    "follow all Apply laws," which afterWord("include") {
      "associative composition" in {
        check {
          forAll { (fa: F[A], fab: F[A => B], fbc: F[B => C]) =>
            applyLaws.applyAssociativeComposition(fa, fab, fbc).isEqual
          }
        }
      }
    }
  }
}
