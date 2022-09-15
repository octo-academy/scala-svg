package scalasvg.parser.primitive

import org.scalatest.matchers.must.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec
import scalasvg.parser.internal.Failed.ParseError
import scalasvg.parser.internal.Result
import scalasvg.parser.internal.cursor.Cursor
import scalasvg.parser.internal.input.Input
import scalasvg.parser.internal.position.Absolute

class CharParsersTest extends AnyWordSpec with Matchers with TableDrivenPropertyChecks {

  "CharParsers".which {
    "AnyChar" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"   ),
        ("a"     , Right(Result('a', Cursor(Input("a"), Absolute(1)))), "a letter"      ),
        ("7"     , Right(Result('7', Cursor(Input("7"), Absolute(1)))), "a digit number"),
        ("/"     , Right(Result('/', Cursor(Input("/"), Absolute(1)))), "a symbol"      )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.AnyChar.run(cursor) must be(expected)
        }
      }
    }

    "Space" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"),
        (" "     , Right(Result(' ', Cursor(Input(" "), Absolute(1)))), "a spacer"   )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.Space.run(cursor) must be(expected)
        }
      }
    }

    "Tab" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                           , "description" ),
        ("\t"    , Right(Result('\t', Cursor(Input("\t"), Absolute(1)))), "a tabulation")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.Tab.run(cursor) must be(expected)
        }
      }
    }

    "LF" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                           , "description"),
        ("\n"    , Right(Result('\n', Cursor(Input("\n"), Absolute(1)))), "a line feed")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.LF.run(cursor) must be(expected)
        }
      }
    }

    "CR" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                           , "description"   ),
        ("\r"    , Right(Result('\r', Cursor(Input("\r"), Absolute(1)))), "a caret return")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.CR.run(cursor) must be(expected)
        }
      }
    }

    "CRLF" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                             , "description"                           ),
        ("\r\n"  , Right(Result('\n', Cursor(Input("\r\n"), Absolute(2)))), "a caret return followed by a line feed")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.NewLine.run(cursor) must be(expected)
        }
      }
    }

    "NewLine" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                             , "description"                           ),
        ("\r"    , Left(ParseError(Cursor(Input("\r"), Absolute(1))))     , "a caret return"                        ),
        ("\n"    , Right(Result('\n', Cursor(Input("\n"), Absolute(1))))  , "a line feed"                           ),
        ("\r\n"  , Right(Result('\n', Cursor(Input("\r\n"), Absolute(2)))), "a caret return followed by a line feed")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.NewLine.run(cursor) must be(expected)
        }
      }
    }

    "Upper" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"         ),
        ("A"     , Right(Result('A', Cursor(Input("A"), Absolute(1)))), "an upper case letter"),
        ("a"     , Left(ParseError(Cursor(Input("a"), Absolute(0))))  , "a lower case letter" )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.Upper.run(cursor) must be(expected)
        }
      }
    }

    "Lower" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"         ),
        ("a"     , Right(Result('a', Cursor(Input("a"), Absolute(1)))), "a lower case letter" ),
        ("A"     , Left(ParseError(Cursor(Input("A"), Absolute(0))))  , "an upper case letter")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.Lower.run(cursor) must be(expected)
        }
      }
    }

    "Letter" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"         ),
        ("A"     , Right(Result('A', Cursor(Input("A"), Absolute(1)))), "an upper case letter"),
        ("a"     , Right(Result('a', Cursor(Input("a"), Absolute(1)))), "a lower case letter" ),
        ("7"     , Left(ParseError(Cursor(Input("7"), Absolute(0))))  , "a digit"             )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.Letter.run(cursor) must be(expected)
        }
      }
    }

    "Digit" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"        ),
        ("a"     , Left(ParseError(Cursor(Input("a"), Absolute(0))))  , "a lower case letter"),
        ("7"     , Right(Result('7', Cursor(Input("7"), Absolute(1)))), "a digit"            )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.Digit.run(cursor) must be(expected)
        }
      }
    }

    "AlphaNum" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"        ),
        ("a"     , Right(Result('a', Cursor(Input("a"), Absolute(1)))), "a lower case letter"),
        ("7"     , Right(Result('7', Cursor(Input("7"), Absolute(1)))), "a digit"            )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          CharParsers.AlphaNum.run(cursor) must be(expected)
        }
      }
    }

    "oneOf" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"),
        ("a"     , Right(Result('a', Cursor(Input("a"), Absolute(1)))), "a letter a" ),
        ("b"     , Right(Result('b', Cursor(Input("b"), Absolute(1)))), "a letter b" ),
        ("c"     , Right(Result('c', Cursor(Input("c"), Absolute(1)))), "a letter c" ),
        ("d"     , Left(ParseError(Cursor(Input("d"), Absolute(0))))  , "a letter d" )
        // format: on
      )

      "passed a string" should {
        forEvery(cases) { (cursor, expected, description) =>
          description in {
            CharParsers.oneOf("abc").run(cursor) must be(expected)
          }
        }
      }

      "passed a list of chars" should {
        forEvery(cases) { (cursor, expected, description) =>
          description in {
            CharParsers.oneOf('a', 'b', 'c').run(cursor) must be(expected)
          }
        }
      }
    }

    "noneOf" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                         , "description"),
        ("a"     , Left(ParseError(Cursor(Input("a"), Absolute(0))))  , "a letter a" ),
        ("b"     , Left(ParseError(Cursor(Input("b"), Absolute(0))))  , "a letter b" ),
        ("c"     , Left(ParseError(Cursor(Input("c"), Absolute(0))))  , "a letter c" ),
        ("d"     , Right(Result('d', Cursor(Input("d"), Absolute(1)))), "a letter d" )
        // format: on
      )

      "passed a string" should {
        forEvery(cases) { (cursor, expected, description) =>
          description in {
            CharParsers.noneOf("abc").run(cursor) must be(expected)
          }
        }
      }

      "passed a list of chars" should {
        forEvery(cases) { (cursor, expected, description) =>
          description in {
            CharParsers.noneOf('a', 'b', 'c').run(cursor) must be(expected)
          }
        }
      }
    }
  }

}
