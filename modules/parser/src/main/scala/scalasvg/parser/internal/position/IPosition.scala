package scalasvg.parser.internal.position

trait IPosition {

  def offset: Int

  def next(char: Char): IPosition
}
