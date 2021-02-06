package scalasvg.example

import scalasvg.element.SVG
import scalasvg.dsl.{ svg, circle, desc, plain }

object Dsl {
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

    println(document)
  }
}
