package scalasvg.lang.laws

import scalasvg.lang.typeclass.Equal

extension [A](lhs: A) {
  def =?=(rhs: A): IsEqual[A] = IsEqual(lhs, rhs)
}

case class IsEqual[A](lhs: A, rhs: A) {
  def isEqual(using eq: Equal[A]): Boolean = eq.equal(lhs, rhs)
}
