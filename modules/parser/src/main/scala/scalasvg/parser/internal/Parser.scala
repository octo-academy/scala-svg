package scalasvg.parser.internal

import scalasvg.lang.typeclass.{ Choice, Monad, Monoid }
import scalasvg.parser.internal.cursor.{ Cursor, ICursor }
import scalasvg.parser.internal.input.{ IInput, Input }
import scalasvg.parser.internal.position.{ Absolute, Detailed }

import java.util.Objects
import scala.annotation.targetName

final case class Parser[O](run: ICursor => Either[Failed, Result[O]]) {

  def run(input: String): Either[Failed, Result[O]] = run(Cursor(Input(input), Absolute(0)))

  def parse(input: String): Either[Failed, O] = parse(Cursor(Input(input), Absolute(0)))

  def parse(cursor: ICursor): Either[Failed, O] =
    run(cursor) match {
      case Right(Result(output, reminder)) =>
        if reminder.isEmpty then Right(output) else Left(Failed.UnconsumedInput(reminder))
      case Left(error)                     => Left(error)
    }

}

object Parser {
  private val _empty: Parser[Nothing] = Parser[Nothing](cursor => Left(Failed.UnconsumedInput(cursor)))

  given ParserMonad: Monad[Parser] with {

    def pure[A](a: A): Parser[A] = Parser(input => Right(Result(a, input)))

    extension [A, AR](parser: Parser[A]) {

      def flatMap(f: A => Parser[AR]): Parser[AR] =
        Parser(input =>
          parser.run(input) match {
            case Right(Result(output, reminder)) => f(output).run(reminder)
            case Left(error)                     => Left(error)
          }
        )

    }

  }

  given ParserMonoid[A]: Monoid[Parser[A]] with {

    def empty: Parser[A] = _empty.asInstanceOf

    extension (a: Parser[A]) {
      @targetName("combine") def |+|(b: Parser[A]): Parser[A] = a <|> b
    }

  }

  given ParserChoice: Choice[Parser] with {

    extension [A, B](a: Parser[A]) {

      @targetName("or") def <|>(b: Parser[B]): Parser[A | B] =
        Parser(input =>
          a.run(input) match {
            case result: Right[Failed, Result[A]] => result
            case Left(_) => b.run(input)
          }
        )

    }

  }

}
