package scalasvg.lang.typeclass

trait InstanceSummoner[F[_]] {
  final def apply[A](using F: F[A]): F[A] = F
}
