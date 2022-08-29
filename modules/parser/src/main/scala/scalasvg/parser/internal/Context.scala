package scalasvg.parser.internal

import math.Ordered.orderingToOrdered

final class Context {
  private var cachedError: Option[Result.Failure[Nothing]] = None

  def error(position: Position, message: => String): Result.Failure[Nothing] =
    cachedError match {
      case Some(error) if error.position >= position =>
        error
      case _                                         =>
        val error: Result.Failure[Nothing] = Result.Failure(position, message)
        cachedError = Some(error)
        error
    }

}
