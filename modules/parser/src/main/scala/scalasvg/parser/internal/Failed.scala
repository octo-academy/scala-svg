package scalasvg.parser.internal

import scalasvg.parser.internal.cursor.ICursor
import scalasvg.parser.internal.input.IInput
import scalasvg.parser.internal.position.IPosition

enum Failed {
  case UnconsumedInput(input: ICursor)
  case ParseError(cursor: ICursor)
}
