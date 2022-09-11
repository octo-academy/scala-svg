package scalasvg.parser.primitive

import org.scalatest.matchers.must.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec
import scalasvg.parser.internal.Result
import scalasvg.parser.internal.cursor.Cursor
import scalasvg.parser.internal.input.Input
import scalasvg.parser.internal.position.Absolute

class NumberParsersTest extends AnyWordSpec with Matchers with TableDrivenPropertyChecks {

  "NumberParsers".which {
    "nat" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                        , "description"                 ),
        ( "7"   , Some(Result(7 , Cursor(Input("7"), Absolute(1)))) , "a one digit number"          ),
        ("42"   , Some(Result(42, Cursor(Input("42"), Absolute(2)))), "multiple digit number"       ),
        ("a7"   , None                                              , "a letter followed by a digit")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          NumberParsers.nat.run(cursor) must be(expected)
        }
      }
    }

    "int" when {
      val cases = Table(
        // format: off
        ("cursor", "expected"                                          , "description"                 ),
        (  "7"  , Some(Result(7  , Cursor(Input("7"), Absolute(1))))  , "a one digit number"          ),
        ( "42"  , Some(Result(42 , Cursor(Input("42"), Absolute(2)))) , "multiple digit number"       ),
        ("+42"  , Some(Result(42 , Cursor(Input("+42"), Absolute(3)))), "a plus sign prefixed number" ),
        ("-42"  , Some(Result(-42, Cursor(Input("-42"), Absolute(3)))), "a minus sign prefixed number"),
        ( "a7"  , None                                                , "a letter followed by a digit")
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          NumberParsers.int.run(cursor) must be(expected)
        }
      }
    }
  }

}
