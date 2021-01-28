package scalasvg.lang.typeclass

trait Applicative[F[_]] extends Apply[F] {
  def pure[A](a: A): F[A]
}

object Applicative extends ContainerLikeInstanceSummoner[Applicative]
