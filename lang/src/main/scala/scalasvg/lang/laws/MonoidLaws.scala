package scalasvg.lang.laws

import scalasvg.lang.typeclass.Monoid

trait MonoidLaws[A](using m: Monoid[A]) {

  def combineRightIdentity(x: A): IsEqual[A] =
    (x |+| m.empty) =?= x

  def combineLeftIdentity(x: A): IsEqual[A] =
    (m.empty |+| x) =?= x
}

object MonoidLaws {
  def apply[A](using Monoid[A]): MonoidLaws[A] = new MonoidLaws[A] {}
}
