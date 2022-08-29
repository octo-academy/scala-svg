package scalasvg.parser

import scalasvg.lang.typeclass.{ Applicative, Monoid }
import scalasvg.parser.internal.{ Parser, Result }

trait CharParsers {
  lazy val AnyChar:  Parser[Char] = satisfy(_ => true)
  lazy val Space:    Parser[Char] = char(' ')
  lazy val Tab:      Parser[Char] = char('\t')
  lazy val LF:       Parser[Char] = char('\n')
  lazy val CR:       Parser[Char] = char('\r')
  lazy val CRLF:     Parser[Char] = CR *> LF
  lazy val NewLine:  Parser[Char] = LF |+| CRLF
  lazy val Upper:    Parser[Char] = satisfy(_.isUpper)
  lazy val Lower:    Parser[Char] = satisfy(_.isLower)
  lazy val Letter:   Parser[Char] = satisfy(_.isLetter)
  lazy val Digit:    Parser[Char] = satisfy(_.isDigit)
  lazy val AlphaNum: Parser[Char] = satisfy(_.isLetterOrDigit)

  lazy val Item:     Parser[Char] = Parser { (position, context, input) =>
    input.next match {
      case Right((char, reminder)) => Result.Success(position.next(char), context, reminder, char)
      case Left(error)             => context.error(position, error)
    }
  }

  final def satisfy(predicate: Char => Boolean): Parser[Char] =
    for {
      c <- Item
      _ <- if (predicate(c)) Applicative[Parser].pure(c) else Monoid[Parser[Char]].empty
    } yield c

  final def char(expected: Char): Parser[Char] = satisfy(_ == expected)

  final def oneOf(chars: String): Parser[Char] = satisfy(chars.contains(_))

  final def oneOf(chars: Char*): Parser[Char] = satisfy(chars.contains(_))

  final def noneOf(chars: String): Parser[Char] = satisfy(!chars.contains(_))

  final def noneOf(chars: Char*): Parser[Char] = satisfy(!chars.contains(_))
}

object CharParsers extends CharParsers
