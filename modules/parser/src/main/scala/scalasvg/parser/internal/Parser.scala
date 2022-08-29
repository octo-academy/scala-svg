package scalasvg.parser.internal

import scalasvg.lang.typeclass.{ Applicative, Choice, Monad, Monoid, Semigroup }
import scalasvg.parser.internal.Parser

import scala.annotation.targetName

final case class Parser[O](val run: Parser.Run[O]) {

  def parse(stream: BufferedStream): Either[String, O] =
    run(Position(0, 0, 0), new Context(), stream) match {
      case Result.Failure(position, error)     =>
        val line   = position.row + 1
        val column = position.column + 1
        Left(s"""Error "${error}" at line ${line} column ${column}""")
      case Result.Success(_, _, input, output) =>
        if input.isEmpty then Right(output) else Left(s"""Unconsumed input: "${input.rest}"""")
    }

}

object Parser {
  type Run[O] = (Position, Context, BufferedStream) => Result[O]

  given ParserMonad: Monad[Parser] with {

    def pure[O](a: O): Parser[O] =
      Parser { (position, context, input) =>
        Result.Success(position, context, input, a)
      }

    extension [O, R](parser: Parser[O]) {

      def flatMap(f: O => Parser[R]): Parser[R] =
        Parser { (position, context, input) =>
          parser.run(position, context, input) match {
            case x: Result.Success[O] => f(x.output).run(x.position, x.context, x.input)
            case x: Result.Failure[O] => Result.Failure(x.position, x.error)
          }
        }

    }

  }

  given ParserMonoid[A]: Monoid[Parser[A]] with {
    def empty: Parser[A] = Parser((position, context, input) => context.error(position, "Parser.empty"))

    extension (a: Parser[A]) {
      @targetName("combine") def |+|(b: Parser[A]): Parser[A] = a <|> b
    }

  }

  given ParserChoice: Choice[Parser] with {

    extension [A, B](a: Parser[A]) {

      @targetName("or") def <|>(b: Parser[B]): Parser[A | B] =
        Parser { (position, context, input) =>
          a.run(position, context, input) match {
            case x: Result.Success[A] => x
            case x: Result.Failure[A] => b.run(position, context, input)
          }
        }

    }

  }

}
