package scalasvg.lang.typeclass

import scala.annotation.targetName

package object typeclass {
  type Id[A] = A

  given IdMonad: Monad[Id] with {
    def pure[A](a: A): Id[A] = a

    extension[A, B](f: Id[A => B]) @targetName("ap") override def <*>(a: Id[A]): Id[B] = f(a)

    extension[A, B](a: Id[A]) {
      override def map(f: A => B): Id[B] = f(a)

      def flatMap(f: A => Id[B]): Id[B] = f(a)
    }
  }
}
