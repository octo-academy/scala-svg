package scalasvg.parser.internal.cursor

import scalasvg.parser.internal.{ Failed, Result }
import scalasvg.parser.internal.input.IInput
import scalasvg.parser.internal.position.IPosition

final case class Cursor(
  override val input:    IInput,
  override val position: IPosition
) extends ICursor {

  override def next: Either[Failed, Result[Char]] =
    input.next(position) match {
      case Some(char) => Right(Result(char, Cursor(input, position.next(char))))
      case None       => Left(Failed.ParseError(this))
    }

  override def reminder: String = input.reminder(position)

  override def isEmpty: Boolean = reminder.isEmpty
}
