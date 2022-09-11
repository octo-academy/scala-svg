package scalasvg.parser.primitive

import scalasvg.lang.std.ListInstances.given
import scalasvg.parser.internal.{ Parser, Result }
import scalasvg.parser.primitive.CharParsers.char

trait StringParsers {
  final def string(str: String): Parser[String] = str.map(char).toList.sequence.map(_.mkString)
}

object StringParsers extends StringParsers
