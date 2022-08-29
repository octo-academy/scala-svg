package scalasvg.lang.typeclass

trait Monoid[A] extends Semigroup[A] {
  def empty: A
}

object Monoid extends InstanceSummoner[Monoid]
