package scalasvg.parser.internal.input

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import scalasvg.parser.internal.cursor.Cursor
import scalasvg.parser.internal.position.{ Absolute, Detailed }

class InputTest extends AnyWordSpec with Matchers {

  "Input" when {
    "content is empty line" should {
      "be empty" in {
        Cursor(Input(""), Absolute(0)).isEmpty must be(true)
      }

      "not be non empty" in {
        Cursor(Input(""), Absolute(0)).nonEmpty must be(false)
      }

      "have an empty reminder" in {
        Cursor(Input(""), Absolute(0)).reminder must be(empty)
      }

      "return None when the next element requested" in {
        Cursor(Input(""), Absolute(0)).next must be(None)
      }
    }

    "content is a word" should {
      "not be empty" in {
        Cursor(Input("az"), Absolute(0)).isEmpty must be(false)
      }

      "be non empty" in {
        Cursor(Input("az"), Absolute(0)).nonEmpty must be(true)
      }

      "have a reminder" when {
        "position is at the beginning should return the whole content" in {
          Cursor(Input("az"), Absolute(0)).reminder must be("az")
        }

        "position is at the middle should return the second half of the content" in {
          Cursor(Input("az"), Absolute(1)).reminder must be("z")
        }

        "position is at the end should return an empty string" in {
          Cursor(Input("az"), Absolute(2)).reminder must be("")
        }
      }

      "return a next element" when {
        "position is at the beginning" in {
          Cursor(Input("az"), Absolute(0)).next must be(Some(('a', Cursor(Input("az"), Absolute(1)))))
        }

        "position is at the middle" in {
          Cursor(Input("az"), Absolute(1)).next must be(Some(('z', Cursor(Input("az"), Absolute(2)))))
        }

        "position is at the end" in {
          Cursor(Input("az"), Absolute(2)).next must be(None)
        }
      }
    }

    "content is a multiline text" should {
      "not be empty" in {
        Cursor(Input("one\ntwo"), Absolute(0)).isEmpty must be(false)
      }

      "be non empty" in {
        Cursor(Input("one\ntwo"), Absolute(0)).nonEmpty must be(true)
      }

      "have a reminder" when {
        "position is at the beginning should return the whole content" in {
          Cursor(Input("one\ntwo"), Absolute(0)).reminder must be("one\ntwo")
        }

        "position is at the middle should return the second half of the content" in {
          Cursor(Input("one\ntwo"), Absolute(3)).reminder must be("\ntwo")
        }

        "position is at the end should return an empty string" in {
          Cursor(Input("one\ntwo"), Absolute(7)).reminder must be("")
        }
      }

      "return a next element" when {
        "position is at the beginning" in {
          Cursor(Input("one\ntwo"), Detailed(Absolute(0), 0, 0)).next must be(
            Some(('o', Cursor(Input("one\ntwo"), Detailed(Absolute(1), 0, 1))))
          )
        }

        "position is at the newline character" in {
          Cursor(Input("one\ntwo"), Detailed(Absolute(3), 0, 3)).next must be(
            Some(('\n', Cursor(Input("one\ntwo"), Detailed(Absolute(4), 1, 0))))
          )
        }

        "position is at the end" in {
          Cursor(Input("one\ntwo"), Detailed(Absolute(7), 1, 4)).next must be(None)
        }
      }
    }
  }

}
