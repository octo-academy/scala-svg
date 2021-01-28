package scalasvg.parser

import scalasvg.lang.std.ListInstances.{ given }
import scalasvg.lang.typeclass.Monoid
import scalasvg.parser.internal.Parser

trait Combinators {
  final def many[O](parser: Parser[O]): Parser[List[O]] =
    some(parser) |+| Monoid[Parser[List[O]]].empty

  final def some[O](parser: Parser[O]): Parser[List[O]] =
    for {
      head <- parser
      tail <- many(parser)
    } yield head :: tail
}

object Combinators extends Combinators
