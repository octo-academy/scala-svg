package scalasvg.example

import scalasvg.parser.CharParsers.{ AnyChar, Digit, char }
import scalasvg.parser.Combinators.{ many, optional, some }
import scalasvg.parser.NumberParsers.int
import scalasvg.parser.StringParsers.string
import scalasvg.parser.internal.{ BufferedStream, Parser }

object Parser {
  def main(args: Array[String]): Unit = {
    val parser: Parser[(Char, Int | String | Char, Char)] = for {
      a <- char('a')
      b <- int <|> string("bla") <|> AnyChar
      c <- char('c')
    } yield (a, b, c)
    
    println(parser.parse(BufferedStream("a-7c")))   // Right(('a', -7   , 'c'))
    println(parser.parse(BufferedStream("ablac")))  // Right(('a', "bla", 'c'))
    println(parser.parse(BufferedStream("aXc")))    // Right(('a', 'X'  , 'c'))
    println(parser.parse(BufferedStream("a--c")))   // Left(Error "Parser.empty" at line 1 column 4) @todo: different error
    println(parser.parse(BufferedStream("a")))      // Left(Error "Unexpected end of input" at line 1 column 2)
    println(parser.parse(BufferedStream("a7cbla"))) // Left(Unconsumed input: "bla")
  }
}
