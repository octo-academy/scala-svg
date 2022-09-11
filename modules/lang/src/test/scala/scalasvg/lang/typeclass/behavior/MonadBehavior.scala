package scalasvg.lang.typeclass.behavior

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import scalasvg.lang.laws.{IsEqual, MonadLaws}
import scalasvg.lang.typeclass.{Equal, Monad}

trait MonadBehavior[F[_]] {
  self: AnyWordSpec with Matchers with Checkers =>
  
  def monad[A, B](using Arbitrary[A], Arbitrary[A => F[B]], Arbitrary[F[A]], Equal[F[A]], Equal[F[B]], Monad[F]): Unit = {
    val monadLaws = MonadLaws[F]
    
    "follow all Monad laws," which afterWord("include") {
      "left identity" in {
        check {
          forAll { (a: A, f: A => F[B]) =>
            monadLaws.monadLeftIdentity(a, f).isEqual
          }
        }
      }
      "right identity" in {
        check {
          forAll { (fa: F[A]) =>
            monadLaws.monadRightIdentity(fa).isEqual
          }
        }
      }
    }
  }
}
