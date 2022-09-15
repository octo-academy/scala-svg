package scalasvg.parser.internal.cursor

import scalasvg.parser.internal.input.IInput
import scalasvg.parser.internal.position.IPosition
import scalasvg.parser.internal.{ Failed, Result }

trait ICursor {
  def input:    IInput
  def position: IPosition
  def next:     Either[Failed, Result[Char]]
  def isEmpty:  Boolean
  def nonEmpty: Boolean = !isEmpty
  def reminder: String
}
