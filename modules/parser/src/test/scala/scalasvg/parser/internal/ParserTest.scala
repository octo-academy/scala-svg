package scalasvg.parser.internal

import org.scalacheck.{ Arbitrary, Gen }
import org.scalacheck.Arbitrary.arbitrary
import org.scalacheck.rng.Seed
import org.scalatest.wordspec.AnyWordSpec
import scalasvg.lang.typeclass.{ Applicative, ContainerLikeLawsTest, Equal }
import scalasvg.parser.internal.Parser.given

class ParserTest extends ContainerLikeLawsTest[Parser] {

  "Parser" should {
    behave.like(functor[String, Int, Float])
    behave.like(monad[Double, Byte])
    behave.like(monoid[Int])
    behave.like(apply[Long, Double, String])
    behave.like(applicative[Int, Byte, Float])
    behave.like(flatMap[String, Long, Byte])
  }

  given ParserArbitrary[A](using Arbitrary[A]): Arbitrary[Parser[A]] =
    Arbitrary(
      for {
        a <- Arbitrary.arbitrary[A]
      } yield Applicative[Parser].pure(a)
    )

  given ParserEqual[A]: Equal[Parser[A]] with {
    private val seed: Seed = Seed.apply(1)
    private val stringsGen = Gen.alphaNumStr
    private val strings    = stringsGen.apply(Gen.Parameters.default, seed)

    def equal(x: Parser[A], y: Parser[A]): Boolean =
      strings.forall { text =>
        (x.parse(text), y.parse(text)) match {
          case (Right(xr), Right(yr)) => xr == yr
          case (xr, yr)               => xr == yr
        }
      }

  }

}
