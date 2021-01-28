package scalasvg.parser

import scalasvg.parser.internal.{ Parser, Result }
import scalasvg.parser.CharParsers.char
import scalasvg.lang.std.ListInstances.{ given }

trait StringParsers {
  final def string(str: String): Parser[String] = str.map(char).toList.sequence.map(_.mkString)
}

object StringParsers extends StringParsers
