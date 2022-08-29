package scalasvg.lang.typeclass

trait Functor[F[_]] {
  def void[A](fa: F[A]): F[Unit] = fa.as(())

  extension [A, B](fa: F[A]) {
    def map(f: A => B): F[B]

    def as(b: B): F[B] = fa.map(_ => b)
  }

}

object Functor extends ContainerLikeInstanceSummoner[Functor]
