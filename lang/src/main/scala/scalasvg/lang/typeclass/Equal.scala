package scalasvg.lang.typeclass

trait Equal[A] {
  def equal(x: A, y: A): Boolean

  final def notEqual(x: A, y: A): Boolean = !equal(x, y)
}

object Equal extends InstanceSummoner[Equal]
