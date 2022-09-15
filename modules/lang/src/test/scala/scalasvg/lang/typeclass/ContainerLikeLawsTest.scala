package scalasvg.lang.typeclass

import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.scalacheck.Checkers
import scalasvg.lang.typeclass.*
import scalasvg.lang.typeclass.behavior.{
  ApplicativeBehavior,
  ApplyBehavior,
  FlatMapBehavior,
  FunctorBehavior,
  MonadBehavior,
  MonoidConteinerLikeBehavior
}

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
