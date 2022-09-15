package scalasvg.parser.internal

import scalasvg.parser.internal.cursor.ICursor

final case class Result[+A](output: A, input: ICursor)
