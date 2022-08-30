package scalasvg.lang.laws

import scalasvg.lang.typeclass.Applicative

trait ApplicativeLaws[F[_]](using F: Applicative[F]) {
  import F.pure

  def applicativeIdentity[A](fa: F[A]): IsEqual[F[A]] = (pure((a: A) => a)) <*> fa =?= fa

  def applicativeComposition[A, B, C](fa: F[A], fab: F[A => B], fbc: F[B => C]): IsEqual[F[C]] =
    fbc <*> (fab <*> fa) =?= (((pure((bc: B => C) => (ab: A => B) => ab.andThen(bc))) <*> fbc) <*> fab) <*> fa

  def applicativeHomomorphism[A, B](a: A, f: A => B): IsEqual[F[B]] = pure(f) <*> pure(a) =?= pure(f(a))

  def applicativeInterchange[A, B](a: A, fab: F[A => B]): IsEqual[F[B]] =
    fab <*> pure(a) =?= (pure((f: A => B) => f(a))) <*> fab

  def applicativeMapConsistency[A, B](fa: F[A], f: A => B): IsEqual[F[B]] = fa.map(f) =?= (pure(f)) <*> fa
}

object ApplicativeLaws {
  def apply[F[_]](using Applicative[F]): ApplicativeLaws[F] = new ApplicativeLaws[F] {}
}
