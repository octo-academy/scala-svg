package scalasvg.lang.typeclass

import scala.annotation.targetName

trait FlatMap[F[_]] extends Apply[F] {

  extension [A](ffa: F[F[A]]) {
    def flatten: F[A] = ffa.flatMap(identity)
  }

  extension [A, B](fa: F[A]) {
    def flatMap(f: A => F[B]): F[B]
  }

  extension [A, B](ff: F[A => B]) {
    @targetName("ap") def <*>(fa: F[A]): F[B] = ff.flatMap(f => fa.map(f))
  }

}

object FlatMap extends ContainerLikeInstanceSummoner[FlatMap]
