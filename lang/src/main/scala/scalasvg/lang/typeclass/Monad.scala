package scalasvg.lang.typeclass

trait Monad[F[_]] extends FlatMap[F] with Applicative[F] {
  extension [A, B](fa: F[A]) {
    def map(f: A => B): F[B] = fa.flatMap(a => pure(f(a)))
  }
}

object Monad extends ContainerLikeInstanceSummoner[Monad]
