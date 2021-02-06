package scalasvg.parser

import scalasvg.parser.internal.{ Parser, Result }
import scalasvg.parser.CharParsers.char
import scalasvg.lang.std.ListInstances.{ given }
import scalasvg.parser.Combinators.token

trait StringParsers {
  final def string(str: String): Parser[String] = str.map(char).toList.sequence.map(_.mkString)

  final def symbol(str: String): Parser[String] = token(string(str))
}

object StringParsers extends StringParsers
