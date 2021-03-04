package scalasvg.lang.std

import scalasvg.lang.typeclass._

import scala.annotation.targetName

trait ListInstances {
  given ListMonoid[A]: Monoid[List[A]] with {
    val empty: List[A] = Nil

    extension (a: List[A]) @targetName("combine") def |+|(b: List[A]): List[A] = a ++ b
  }
  
  given ListMonad: Monad[List] with {
    def pure[A](a: A): List[A] = List(a)

    extension [A, B](fa: List[A]) {
      def flatMap(f: A => List[B]): List[B] = fa.foldRight(Monoid[List[B]].empty)((a, acc) => f(a) ++ acc)
    }
  }

  given ListTraversable: Traverse[List] with {
    extension[G[_], A, B] (fa: List[A])(using Applicative[G]) {
      def traverse(f: A => G[B]): G[List[B]] = fa.foldRight(Applicative[G].pure(Monoid[List[B]].empty)) { (a, acc) =>
        f(a).map2(acc)(_ :: _)
      }
    }

    extension [A, B](fa: List[A]) {
      def foldLeft(b: B)(f: (B, A) => B): B = fa.foldLeft(b)(f)

      def foldRight(b: B)(f: (A, B) => B): B = fa.foldRight(b)(f)
    }
  }
  
  given ListEqual[A](using A: Equal[A]): Equal[List[A]] with {
    def equal(x: List[A], y: List[A]) = {
      x.size == y.size && {
        x.zip(y).forall { case (xx, yy) => A.equal(xx, yy) }
      }
    }
  }
}

object ListInstances extends ListInstances
