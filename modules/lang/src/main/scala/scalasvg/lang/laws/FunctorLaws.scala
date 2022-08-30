package scalasvg.lang.laws

import scalasvg.lang.typeclass.Functor

trait FunctorLaws[F[_]](using Functor[F]) {

  def functorIdentity[A](fa: F[A]): IsEqual[F[A]] = fa.map(identity) =?= fa

  def functorComposition[A, B, C](fa: F[A], f: A => B, g: B => C): IsEqual[F[C]] =
    fa.map(f).map(g) =?= fa.map(f.andThen(g))

}

object FunctorLaws {
  def apply[F[_]](using Functor[F]): FunctorLaws[F] = new FunctorLaws[F] {}
}
