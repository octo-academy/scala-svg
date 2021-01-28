package scalasvg.lang.typeclass

import scala.annotation.targetName

enum Maybe[+T] {
  case Just(value: T)
  case Nothing

  final def isDefined: Boolean = this match {
    case Nothing => false
    case _       => true
  }

  final def orElse[U >: T](alternative: Maybe[U]): Maybe[U] = this match {
    case Just(a) => Just(a)
    case Nothing => alternative
  }

  final def orElse[U >: T](alternative: U): Maybe[U] = this match {
    case Just(a) => Just(a)
    case Nothing => Just(alternative)
  }
}

object Maybe {
  final def empty[A](using F: Monoid[Maybe[A]]) = F.empty

  final def apply[T >: Null](nullable: T): Maybe[T] = if (nullable == null) then Nothing else Just(nullable)

  given MaybeMonad: Monad[Maybe] with {
    def pure[A](a: A): Maybe[A] = Just(a)

    extension [A, B](ff: Maybe[A => B]) {
      @targetName("ap") override def <*>(fa: Maybe[A]): Maybe[B] =
        (ff, fa) match {
          case (Just(f), Just(a)) => Just(f(a))
          case _                  => Nothing
        }
    }

    extension [A, B](fa: Maybe[A]) {
      override def map(f: A => B): Maybe[B] =
        fa match {
          case Just(a) => Just(f(a))
          case Nothing => Nothing
        }

      def flatMap(f: A => Maybe[B]): Maybe[B] =
        fa match {
          case Just(a) => f(a)
          case Nothing => Nothing
        }
    }
  }

  given MaybeMonoid[A](using Semigroup[A]): Monoid[Maybe[A]] with {
    def empty: Maybe[A] = Nothing

    extension (fa: Maybe[A]) {
      @targetName("combine") def |+| (fb: Maybe[A]): Maybe[A] =
        (fa, fb) match {
          case (Just(a), Just(b)) => Just(a |+| b)
          case (Just(a), Nothing) => Just(a)
          case (Nothing, Just(b)) => Just(b)
          case (Nothing, Nothing) => Nothing
        }
    }
  }

  given MaybeFoldable: Foldable[Maybe] with {
    extension [A, B](fa: Maybe[A]) {
      def foldLeft(b: B)(f: (B, A) => B): B = fa match {
        case Just(a) => f(b, a)
        case Nothing => b
      }

      def foldRight(b: B)(f: (A, B) => B): B = foldLeft(b)((a, b) => f(b, a))
    }
  }

  given MaybeTraverse: Traverse[Maybe] with {

    extension [A, B](fa: Maybe[A]) {
      def foldLeft(b: B)(f: (B, A) => B): B = ???

      def foldRight(b: B)(f: (A, B) => B): B = ???
    }

    extension [G[_], A, B](fa: Maybe[A])(using F: Applicative[G]) def traverse(f: A => G[B]): G[Maybe[B]] =
      fa match {
        case Just(a) => f(a).map(Just(_))
        case Nothing => F.pure(Nothing)
      }
  }
}
