package scalasvg.parser.internal.input

import scalasvg.parser.internal.position.IPosition

final case class Input(content: String) extends IInput {

  def next(position: IPosition): Option[Char] = Option.when(position.offset < content.length)(content(position.offset))

  def reminder(position: IPosition): String = content.slice(position.offset, content.length)
}
