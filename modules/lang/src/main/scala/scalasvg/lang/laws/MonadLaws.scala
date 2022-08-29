package scalasvg.lang.laws

import scalasvg.lang.typeclass.Monad

trait MonadLaws[F[_]](using m: Monad[F]) {

  def monadLeftIdentity[A, B](a: A, f: A => F[B]): IsEqual[F[B]] = m.pure(a).flatMap(f) =?= f(a)

  def monadRightIdentity[A](fa: F[A]): IsEqual[F[A]] = fa.flatMap(a => m.pure(a)) =?= fa
}

object MonadLaws {
  def apply[F[_]](using Monad[F]): MonadLaws[F] = new MonadLaws[F] {}
}
