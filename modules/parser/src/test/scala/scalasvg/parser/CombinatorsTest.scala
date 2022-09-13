package scalasvg.parser

import org.scalatest.matchers.must.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec
import scalasvg.parser.internal.Failed.{ ParseError, UnconsumedInput }
import scalasvg.parser.internal.cursor.Cursor
import scalasvg.parser.internal.input.Input
import scalasvg.parser.internal.position.Absolute
import scalasvg.parser.internal.{ Parser, Result }
import scalasvg.parser.primitive.CharParsers

class CombinatorsTest extends AnyWordSpec with Matchers with TableDrivenPropertyChecks {

  "Combinators".which {
    val parseX = CharParsers.char('x')

    "optional" when {
      val cases = Table(
        // format: off
        ("text", "expected"                                               , "description"     ),
        ("x"   , Right(Result(Some('x'), Cursor(Input("x"), Absolute(1)))), "has desired item"),
        ("a"   , Right(Result(None     , Cursor(Input("a"), Absolute(0)))), "has wrong item"  ),
        (""    , Right(Result(None     , Cursor(Input("" ), Absolute(0)))), "an empty input"  )
        // format: on
      )

      forEvery(cases) { (text, expected, description) =>
        description in {
          Combinators.optional(parseX).run(text) must be(expected)
        }
      }
    }

    "some" when {
      val cases = Table(
        // format: off
        ("input", "expected"                                                     , "description"                                                    ),
        ("xx-"  , Right(Result(Seq('x', 'x'), Cursor(Input("xx-"), Absolute(2)))), "has multiple sequential desired items followed by undesired one"),
        ("xx"   , Right(Result(Seq('x', 'x'), Cursor(Input("xx" ), Absolute(2)))), "has multiple sequential desired items"                          ),
        ("x"    , Right(Result(Seq('x')     , Cursor(Input("x"  ), Absolute(1)))), "has a single desired item"                                      ),
        (""     , Left(ParseError(Cursor(Input(""),Absolute(0))))                , "an empty input"                                                 )
        // format: on
      )

      forEvery(cases) { (input, expected, description) =>
        description in {
          Combinators.some(parseX).run(input) must be(expected)
        }
      }
    }

    "many" when {
      val cases = Table(
        // format: off
        ("input", "expected"                                                     , "description"                                                    ),
        ("xx-"  , Right(Result(Seq('x', 'x'), Cursor(Input("xx-"), Absolute(2)))), "has multiple sequential desired items followed by undesired one"),
        ("xx"   , Right(Result(Seq('x', 'x'), Cursor(Input("xx" ), Absolute(2)))), "has multiple sequential desired items"                          ),
        ("x"    , Right(Result(Seq('x')     , Cursor(Input("x"  ), Absolute(1)))), "has a single desired item"                                      ),
        (""     , Right(Result(Seq()        , Cursor(Input(""   ), Absolute(0)))), "an empty input"                                                 )
        // format: on
      )

      forEvery(cases) { (input, expected, description) =>
        description in {
          Combinators.many(parseX).run(input) must be(expected)
        }
      }
    }

    "between" should behave.like(betweenParser('=', 'x', '='))

    "parentheses" should behave.like(betweenParser(Combinators.parentheses)('(', 'x', ')'))

    "brackets" should behave.like(betweenParser(Combinators.brackets)('[', 'x', ']'))

    "braces" should behave.like(betweenParser(Combinators.braces)('{', 'x', '}'))

    "quotes" should behave.like(betweenParser(Combinators.quotes)('"', 'x', '"'))
  }

  private def betweenParser(open: Char, item: Char, close: Char): Unit =
    betweenParser(Combinators.between(CharParsers.char(open), CharParsers.char(close)))(open, item, close)

  private def betweenParser(combinator: Parser[Char] => Parser[Char])(open: Char, item: Char, close: Char): Unit = {
    val parser    = combinator(CharParsers.char(item))
    val (o, i, c) = (open, item, close)
    val cases     = Table(
      // format: off
      ("input"  , "expected"                                                , "description"                                  ),
      (s"$o$i$c", Right(Result(item, Cursor(Input(s"$o$i$c"), Absolute(3)))), "has a desired item enclosed in open and close"),
      (s"$o$c"  , Left(ParseError(Cursor(Input(s"$o$c"),Absolute(1))))      , "has an open and close but no item"            ),
      (s"$o$i"  , Left(ParseError(Cursor(Input(s"$o$i"),Absolute(2))))      , "has an open and desired item but no close"    ),
      (s"$i$c"  , Left(ParseError(Cursor(Input(s"$i$c"),Absolute(0))))      , "has a desired item and close but no open"     ),
      (s"$i"    , Left(ParseError(Cursor(Input(s"$i"),Absolute(0))))        , "has a desired item alone no open nor close"   )
      // format: on
    )

    forEvery(cases) { (input, expected, description) =>
      description in {
        parser.run(input) must be(expected)
      }
    }
  }

}
