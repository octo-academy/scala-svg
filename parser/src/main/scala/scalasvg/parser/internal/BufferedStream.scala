package scalasvg.parser.internal

final case class BufferedStream(buffer: Array[Char],
                                bufferSize: Int = BufferedStream.DefaultBufferSize,
                                offset: Int) {
                                     
  def isEmpty: Boolean = (buffer.length - 1) <= offset

  def apply(offset: Int): Either[String, (Char, BufferedStream)] =
    applyF[Char](offset, (stream, head, localOffset) => Right((head(localOffset), stream.copy(offset = localOffset))))

  def apply(offset: Int, length: Int): Either[String, (String, BufferedStream)] =
    applyF[String](
      offset + length,
      (stream, head, localOffset) => Right(new String(head.slice(offset, localOffset)), stream.copy(offset = localOffset))
    )

  private def applyF[U](offset: Int,
                        f: (BufferedStream, Array[Char], Int) => Either[String, (U, BufferedStream)]
                        ): Either[String, (U, BufferedStream)] = {
    if (offset < this.offset) {
      Left("Offset cannot be smaller than the current offset of the buffer")
    } else if (isEmpty) {
      Left("Unexpected end of input")
    } else {
      f(this, buffer, offset)
    }
  }
}

object BufferedStream {
  val DefaultBufferSize: Int = 4096

  def apply(string: String): BufferedStream = BufferedStream(string.toCharArray, string.length, 0)
}
