package typeclass.behavior

import scalasvg.lang.laws.{FunctorLaws, IsEqual}
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import org.scalatest.matchers.must.Matchers
import scalasvg.lang.typeclass.{Equal, Functor}
import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll

trait FunctorBehavior[F[_]] {
  self: AnyWordSpec with Matchers with Checkers =>
  
  def functor[A, B, C](using Arbitrary[F[A]], Arbitrary[A => B], Arbitrary[B => C], Equal[F[A]], Equal[F[C]], Functor[F]) = {
    val functorLaws = FunctorLaws[F]
    
    "follow all Functor laws," which afterWord("include") {
      "identity" in {
        check {
          forAll { (fa: F[A]) =>
            functorLaws.functorIdentity(fa).isEqual
          }
        }
      }
      "composition" in {
        check {
          forAll { (fa: F[A], f: A => B, g: B => C) =>
            functorLaws.functorComposition(fa, f, g).isEqual
          }
        }
      }
    }
  }
}
