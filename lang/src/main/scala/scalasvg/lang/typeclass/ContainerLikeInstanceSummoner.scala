package scalasvg.lang.typeclass

trait ContainerLikeInstanceSummoner[F[_[_]]] {
  final def apply[A[_]](using F: F[A]): F[A] = F
}
