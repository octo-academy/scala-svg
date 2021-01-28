package scalasvg.lang.std

import scalasvg.lang.typeclass.Monoid

import scala.annotation.targetName

trait ListInstances {
  given ListMonoid[A]: Monoid[List[A]] with {
    val empty: List[A] = Nil

    extension (a: List[A]) @targetName("combine") def |+|(b: List[A]): List[A] = a ++ b
  }
}

object ListInstances extends ListInstances
