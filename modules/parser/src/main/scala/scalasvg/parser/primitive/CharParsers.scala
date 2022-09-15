package scalasvg.parser.primitive

import scalasvg.lang.typeclass.{ Applicative, Monoid }
import scalasvg.parser.internal.{ Failed, Parser, Result }

trait CharParsers {
  final val AnyChar:  Parser[Char] = satisfy(_ => true)
  final val Space:    Parser[Char] = char(' ')
  final val Tab:      Parser[Char] = char('\t')
  final val LF:       Parser[Char] = char('\n')
  final val CR:       Parser[Char] = char('\r')
  final val CRLF:     Parser[Char] = CR *> LF
  final val NewLine:  Parser[Char] = LF |+| CRLF
  final val Upper:    Parser[Char] = satisfy(_.isUpper)
  final val Lower:    Parser[Char] = satisfy(_.isLower)
  final val Letter:   Parser[Char] = satisfy(_.isLetter)
  final val Digit:    Parser[Char] = satisfy(_.isDigit)
  final val AlphaNum: Parser[Char] = satisfy(_.isLetterOrDigit)

  final val Item: Parser[Char] = Parser(cursor => cursor.next)

  final def satisfy(test: Char => Boolean): Parser[Char] =
    Parser(input => Item.run(input).filterOrElse(x => test(x.output), Failed.ParseError(input)))

  final def char(expected: Char): Parser[Char] = satisfy(_ == expected)

  final def oneOf(chars: String): Parser[Char] = satisfy(chars.contains(_))

  final def oneOf(chars: Char*): Parser[Char] = satisfy(chars.contains(_))

  final def noneOf(chars: String): Parser[Char] = satisfy(!chars.contains(_))

  final def noneOf(chars: Char*): Parser[Char] = satisfy(!chars.contains(_))
}

object CharParsers extends CharParsers
