package scalasvg.parser.internal

final case class BufferedStream(buffer: Array[Char],
                                bufferSize: Int = BufferedStream.DefaultBufferSize,
                                offset: Int) {
                                     
  def isEmpty: Boolean = buffer.length <= offset

  def next: Either[String, (Char, BufferedStream)] = safeNext[Char](buffer(offset))

  private def safeNext[U](portion: => U): Either[String, (U, BufferedStream)] = {
    if (isEmpty) {
      Left("Unexpected end of input")
    } else {
      Right((portion, this.copy(offset = offset + 1)))
    }
  }
}

object BufferedStream {
  val DefaultBufferSize: Int = 4096

  def apply(string: String): BufferedStream = BufferedStream(string.toCharArray, string.length, 0)
}
