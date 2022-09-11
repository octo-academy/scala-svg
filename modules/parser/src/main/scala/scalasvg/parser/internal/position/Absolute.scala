package scalasvg.parser.internal.position

final case class Absolute(override val offset: Int) extends IPosition {

  override def next(char: Char): Absolute = Absolute(offset + 1)
}
