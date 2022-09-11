package scalasvg.example

import scalasvg.parser.primitive.CharParsers.{ AnyChar, Digit, char }
import scalasvg.parser.Combinators.{ many, optional, some }
import scalasvg.parser.primitive.NumberParsers.int
import scalasvg.parser.primitive.StringParsers.string
import scalasvg.parser.internal.Parser
import scalasvg.parser.internal.input.Input

object Parser {

  def main(args: Array[String]): Unit = {
    val parser: Parser[(Char, Int | String | Char, Char)] = for {
      a <- char('a')
      b <- int <|> string("bla") <|> AnyChar
      c <- char('c')
    } yield (a, b, c)

    println(parser.parse("a-7c"))   // Right((a,-7,c))
    println(parser.parse("ablac"))  // Right((a,bla,c))
    println(parser.parse("aXc"))    // Right((a,X,c))
    println(parser.parse("a--c"))   // Left(ParseError(Cursor(Input(a--c),Absolute(2))))
    println(parser.parse("a"))      // Left(ParseError(Cursor(Input(a),Absolute(1))))
    println(parser.parse("a7cbla")) // Left(UnconsumedInput(Cursor(Input(a7cbla),Absolute(3))))
  }

}
