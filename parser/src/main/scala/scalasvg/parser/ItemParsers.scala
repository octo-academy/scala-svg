package scalasvg.parser

import scalasvg.parser.internal.{ Parser, Result }

trait ItemParsers {
  val Item: Parser[Char]  = Parser { (position, context, input) =>
    input.apply(position.absolute) match {
      case Right((char, reminder)) => Result.Success(position.next(char), context, reminder, char)
      case Left(error)             => context.error(position, error)
    }
  }
}

object ItemParsers extends ItemParsers
