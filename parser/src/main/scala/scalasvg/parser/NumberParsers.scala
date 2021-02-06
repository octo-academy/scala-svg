package scalasvg.parser

import scalasvg.parser.internal.Parser
import scalasvg.parser.Combinators.{ optional, some, token }
import scalasvg.parser.CharParsers.{ Digit, char }

trait NumberParsers {
  lazy val nat:     Parser[Int] = some(Digit).map(_.toList.mkString.toInt)
  lazy val natural: Parser[Int] = token(nat)
  lazy val int:     Parser[Int] = for {
    sign   <- optional(char('-') |+| char('+'))
    number <- nat
  } yield sign.fold(number) {
    case '+' =>  number
    case '-' => -number
  }
}

object NumberParsers extends NumberParsers
