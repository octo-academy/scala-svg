package scalasvg.element

sealed trait Element[+T]

object Element {
  trait Empty extends Element[Nothing]
  trait Container[T](content: T) extends Element[T]
}
