package scalasvg.parser.internal.position

final case class Detailed(absolute: Absolute, row: Int, column: Int) extends IPosition {
  override val offset: Int = absolute.offset

  override def next(char: Char): Detailed =
    if char == '\n'
    then Detailed(absolute.next(char), row + 1, 0)
    else Detailed(absolute.next(char), row, column + 1)

}
