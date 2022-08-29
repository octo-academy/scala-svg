package scalasvg.lang.laws

import scalasvg.lang.typeclass.FlatMap

trait FlatMapLaws[F[_]](using m: FlatMap[F]) {
  
  def flatMapAssociativity[A, B, C](fa: F[A], f: A => F[B], g: B => F[C]): IsEqual[F[C]] =
    fa.flatMap(f).flatMap(g) =?= fa.flatMap { a => f(a).flatMap(g) }
}

object FlatMapLaws {
  def apply[F[_]](using FlatMap[F]): FlatMapLaws[F] = new FlatMapLaws[F] {}
}
