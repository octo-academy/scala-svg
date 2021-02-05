package scalasvg.parser.internal

final case class BufferedStream(buffer: Array[Char],
                                bufferSize: Int = BufferedStream.DefaultBufferSize,
                                offset: Int) {
                                     
  def isEmpty: Boolean = buffer.length <= offset

  def apply(offset: Int): Either[String, (Char, BufferedStream)] =
    applyF[Char](offset, (head, localOffset) => head(localOffset))

  def apply(offset: Int, length: Int): Either[String, (String, BufferedStream)] =
    applyF[String](
      offset + length,
      (head, localOffset) => new String(head.slice(offset, localOffset))
    )

  private def applyF[U](offset: Int, f: (Array[Char], Int) => U): Either[String, (U, BufferedStream)] = {
    if (offset < this.offset) {
      Left("Offset cannot be smaller than the current offset of the buffer")
    } else if (isEmpty) {
      Left("Unexpected end of input")
    } else {
      Right((f(buffer, offset), this.copy(offset = offset)))
    }
  }
}

object BufferedStream {
  val DefaultBufferSize: Int = 4096

  def apply(string: String): BufferedStream = BufferedStream(string.toCharArray, string.length, 0)
}
