package scalasvg.parser.internal.input

import scalasvg.parser.internal.position.IPosition

trait IInput {

  def next(position: IPosition): Option[Char]

  def reminder(position: IPosition): String
}
