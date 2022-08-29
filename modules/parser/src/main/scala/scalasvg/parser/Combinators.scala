package scalasvg.parser

import scalasvg.lang.std.ListInstances.given
import scalasvg.lang.typeclass.Applicative
import scalasvg.parser.internal.Parser
import scalasvg.parser.CharParsers.{ Space, char }

trait Combinators {
  final def optional[O](parser: Parser[O]): Parser[Option[O]] = parser.map(Some(_)) |+| Applicative[Parser].pure(None)

  final def many[O](parser: Parser[O]): Parser[List[O]] = some(parser) |+| Applicative[Parser].pure(List[O]())

  final def some[O](parser: Parser[O]): Parser[List[O]] =
    for {
      head <- parser
      tail <- many(parser)
    } yield head :: tail

  final def between[OPEN, CLOSE, A](open: Parser[OPEN], close: Parser[CLOSE])(p: Parser[A]): Parser[A] =
    open *> p <* close

  final def between[OPEN, A](open: Parser[OPEN])(p: Parser[A]): Parser[A] = between(open, open)(p)

  final def token[A](p: Parser[A]): Parser[A] = between(Space, Space)(p)

  final def parentheses[A](p: Parser[A]): Parser[A] = between(char('('), char(')'))(p)

  final def braces[A](p: Parser[A]): Parser[A] = between(char('{'), char('}'))(p)

  final def brackets[A](p: Parser[A]): Parser[A] = between(char('['), char(']'))(p)

  final def quotes[A](p: Parser[A]): Parser[A] = between(char('"'))(p)

}

object Combinators extends Combinators
