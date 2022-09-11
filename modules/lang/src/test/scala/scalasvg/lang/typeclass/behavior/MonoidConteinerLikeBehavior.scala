package scalasvg.lang.typeclass.behavior

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import scalasvg.lang.laws.{IsEqual, MonoidLaws}
import scalasvg.lang.typeclass.{Equal, Monoid}

trait MonoidConteinerLikeBehavior[F[_]] {
  self: AnyWordSpec with Matchers with Checkers =>
  
  def monoid[A](using Arbitrary[F[A]], Equal[F[A]], Monoid[F[A]]): Unit = {
    val monoidLaws = MonoidLaws[F[A]]
    
    "follow all Monoid laws," which afterWord("include"){
      "combine right identity" in {
        check {
          forAll { (a: F[A]) =>
            monoidLaws.combineRightIdentity(a).isEqual
          }
        }
      }
      "combine left identity" in {
        check {
          forAll { (a: F[A]) =>
            monoidLaws.combineLeftIdentity(a).isEqual
          }
        }
      }
    }
  }
}
