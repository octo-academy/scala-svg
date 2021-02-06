package scalasvg.parser.internal

enum Result[+A] {
  case Failure(position: Position, error: String)
  case Success(position: Position, context: Context, input: BufferedStream, output: A)
}
