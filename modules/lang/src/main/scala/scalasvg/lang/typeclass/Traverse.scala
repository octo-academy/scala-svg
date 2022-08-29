package scalasvg.lang.typeclass

trait Traverse[F[_]] extends Foldable[F] {
  extension [G[_], A, B](fa: F[A])(using Applicative[G]) def traverse(f: A => G[B]): G[F[B]]
  
  extension [G[_], A, B](fa: F[G[A]])(using Applicative[G]) def sequence: G[F[A]] = fa.traverse(identity)
}

object Traverse extends ContainerLikeInstanceSummoner[Traverse]
