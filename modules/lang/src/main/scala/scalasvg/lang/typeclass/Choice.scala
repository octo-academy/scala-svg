package scalasvg.lang.typeclass

import scala.annotation.targetName

trait Choice[F[_]] {

  extension [A, B](a: F[A]) {
    @targetName("or") def <|>(b: F[B]): F[A | B]
  }

}

object Choice extends ContainerLikeInstanceSummoner[Choice]
