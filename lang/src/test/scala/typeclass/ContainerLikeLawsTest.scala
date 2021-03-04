package typeclass

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.scalacheck.Checkers
import typeclass.behavior._
import scalasvg.lang.typeclass._

class ContainerLikeLawsTest[F[_]]
  extends AnyWordSpec
    with Matchers
    with Checkers
    with FunctorBehavior[F]
    with MonadBehavior[F]
    with MonoidConteinerLikeBehavior[F]
    with ApplyBehavior[F]
    with ApplicativeBehavior[F]
    with FlatMapBehavior[F]
