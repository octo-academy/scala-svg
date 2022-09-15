package scalasvg.lang.typeclass.behavior

import org.scalacheck.Arbitrary
import org.scalacheck.Prop.forAll
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import scalasvg.lang.laws.{ ApplicativeLaws, IsEqual }
import scalasvg.lang.typeclass.{ Applicative, Equal }

trait ApplicativeBehavior[F[_]] {
  self: AnyWordSpec with Matchers with Checkers =>

  def applicative[A, B, C](using
    Arbitrary[A],
    Arbitrary[A => B],
    Arbitrary[F[A]],
    Arbitrary[F[A => B]],
    Arbitrary[F[B => C]],
    Equal[F[A]],
    Equal[F[B]],
    Equal[F[C]],
    Applicative[F]
  ): Unit = {
    val applicativeLaws = ApplicativeLaws[F]

    "follow all Applicative laws,".which(afterWord("include") {
      "identity" in {
        check {
          forAll { (fa: F[A]) =>
            applicativeLaws.applicativeIdentity(fa).isEqual
          }
        }
      }
      "composition" in {
        check {
          forAll { (fa: F[A], fab: F[A => B], fbc: F[B => C]) =>
            applicativeLaws.applicativeComposition(fa, fab, fbc).isEqual
          }
        }
      }
      "homomorphism" in {
        check {
          forAll { (a: A, f: A => B) =>
            applicativeLaws.applicativeHomomorphism(a, f).isEqual
          }
        }
      }
      "interchange" in {
        check {
          forAll { (a: A, fab: F[A => B]) =>
            applicativeLaws.applicativeInterchange(a, fab).isEqual
          }
        }
      }
      "map consistency" in {
        check {
          forAll { (fa: F[A], f: A => B) =>
            applicativeLaws.applicativeMapConsistency(fa, f).isEqual
          }
        }
      }
    })
  }

}
