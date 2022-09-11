package scalasvg.parser.primitive

import org.scalatest.matchers.must.Matchers
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.wordspec.AnyWordSpec
import scalasvg.parser.internal.Result
import scalasvg.parser.internal.cursor.Cursor
import scalasvg.parser.internal.input.Input
import scalasvg.parser.internal.position.Absolute

class StringParsersTest extends AnyWordSpec with Matchers with TableDrivenPropertyChecks {

  "CharParsers".which {
    "string" when {

      val cases = Table(
        // format: off
        ("cursor"  , "expected"                                                 , "description"           ),
        ("textile", Some(Result("text", Cursor(Input("textile"), Absolute(4)))), "a text and an appendix"),
        ("text"   , Some(Result("text", Cursor(Input("text"), Absolute(4))))   , "a full text"           ),
        ("tex"    , None                                                       , "a text missing a part" )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          StringParsers.string("text").run(cursor) must be(expected)
        }
      }
    }

    "symbol" when {

      val cases = Table(
        // format: off
        ("cursor"  , "expected"                                                 , "description"           ),
        ("textile", Some(Result("text", Cursor(Input("textile"), Absolute(4)))), "a text and an appendix"),
        ("text"   , Some(Result("text", Cursor(Input("text"), Absolute(4))))   , "a full text"           ),
        ("tex"    , None                                                       , "a text missing a part" )
        // format: on
      )

      forEvery(cases) { (cursor, expected, description) =>
        description in {
          StringParsers.string("text").run(cursor) must be(expected)
        }
      }
    }
  }

}
