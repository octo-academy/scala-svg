package scalasvg.parser.primitive

import scalasvg.parser.Combinators.{ optional, some }
import scalasvg.parser.internal.Parser
import scalasvg.parser.primitive.CharParsers.{ Digit, char }

trait NumberParsers {
  val nat: Parser[Int] = some(Digit).map(_.mkString.toInt)

  val int: Parser[Int] = for {
    sign   <- optional(char('-') |+| char('+'))
    number <- nat
  } yield sign.fold(number) {
    case '+' => number
    case '-' => -number
  }

}

object NumberParsers extends NumberParsers
