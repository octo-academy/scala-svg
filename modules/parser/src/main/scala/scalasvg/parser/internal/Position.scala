package scalasvg.parser.internal

import scalasvg.parser.internal.Position
import scala.Ordering

final case class Position(absolute: Int, row: Int, column: Int) {
  def next(string: String): Position = string.foldLeft(this)(_.next(_))

  def next(char: Char): Position =
    if char == '\n' then Position(absolute + 1, row + 1, 1) else Position(absolute + 1, row, column + 1)

}

object Position {
  implicit val OrderPosition: Ordering[Position] = Ordering.by(_.absolute)
}
