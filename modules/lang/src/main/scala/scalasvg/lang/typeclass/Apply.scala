package scalasvg.lang.typeclass

import scala.annotation.targetName

trait Apply[F[_]] extends Functor[F] with Semigroupal[F] {

  extension [A, B](ff: F[A => B]) {
    @targetName("ap") def <*>(fa: F[A]): F[B]
  }

  extension [A, B, Z](fa: F[A]) {
    def map2(fb: F[B])(f: (A, B) => Z): F[Z] = fa.product(fb).map(f.tupled)
  }

  extension [A, B](fa: F[A]) {
    override def product(fb: F[B]): F[(A, B)] = fa.map(a => (b: B) => (a, b)) <*> fb

    @targetName("productL") def <*(fb: F[B]): F[A] = fa.map2(fb)((a, _) => a)

    @targetName("productR") def *>(fb: F[B]): F[B] = fa.map2(fb)((_, b) => b)
  }

}

object Apply extends ContainerLikeInstanceSummoner[Apply]
