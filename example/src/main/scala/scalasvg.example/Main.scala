package scalasvg.example

import scalasvg.element.SVG
import scalasvg.dsl.{ svg, circle, desc, plain }
import scalasvg.parser.Combinators.{ many, some }
import scalasvg.parser.CharParsers.{ char, Digit }
import scalasvg.parser.StringParsers.{ string }
import scalasvg.parser.internal.BufferedStream

object Main {
  def main(args: Array[String]): Unit = {
    val document: SVG =
      svg(viewBox = "0 0 100 100") {
        desc() {
          plain("A description for the document.")
        }
        circle(x = "0", y = "0", r = "5px") {
          desc() {
            plain("A multiline description")
            plain("for the circle.")
          }
        }
      }

//    println(document)

    val parser = for {
      a <- char('a')
      b <- some(Digit)
      c <- char('c')
    } yield (a, b, c)

//    val parser = (char('4') |+| char('a')) *> char('b')

    println(parser.parse(BufferedStream("a42c")))
  }
}
