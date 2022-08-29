package scalasvg.lang.typeclass

import scala.annotation.targetName

trait Semigroup[A] {

  extension (a: A) {
    @targetName("combine") def |+|(b: A): A
  }

}

object Semigroup extends InstanceSummoner[Semigroup]
