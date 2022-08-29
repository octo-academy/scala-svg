package scalasvg.lang.typeclass

trait Foldable[F[_]] {
  extension [A](fa: F[A])(using F: Monoid[A]) def fold: A = fa.foldLeft(F.empty)(_ |+| _)

  extension [A, B](fa: F[A]) {
    def fold(b: B)(f: (B, A) => B): B = foldLeft(b)(f)

    def foldLeft(b: B)(f: (B, A) => B): B

    def foldRight(b: B)(f: (A, B) => B): B
  }

}

object Foldable extends ContainerLikeInstanceSummoner[Foldable]
