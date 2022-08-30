package scalasvg.lang.typeclass

trait Semigroupal[F[_]] {

  extension [A, B](fa: F[A]) {
    def product(fb: F[B]): F[(A, B)]
  }

}

object Semigroupal extends ContainerLikeInstanceSummoner[Semigroupal]
