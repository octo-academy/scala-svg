package scalasvg.parser.internal.cursor

import scalasvg.parser.internal.{ Failed, Result }
import scalasvg.parser.internal.input.IInput
import scalasvg.parser.internal.position.IPosition

trait ICursor {
  def input:    IInput
  def position: IPosition
  def next:     Either[Failed, Result[Char]]
  def isEmpty:  Boolean
  def nonEmpty: Boolean = !isEmpty
  def reminder: String
}
