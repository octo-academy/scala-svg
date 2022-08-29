package scalasvg.lang.laws

import scalasvg.lang.typeclass.Apply

trait ApplyLaws[F[_]](using Apply[F]) {

  def applyAssociativeComposition[A, B, C](fa: F[A], fab: F[A => B], fbc: F[B => C]): IsEqual[F[C]] =
    fbc <*> (fab <*> fa) =?= (fbc.map((bc: B => C) => (ab: A => B) => ab andThen bc) <*> fab) <*> fa
}

object ApplyLaws {
  def apply[F[_]](using Apply[F]): ApplyLaws[F] = new ApplyLaws[F] {}
}
