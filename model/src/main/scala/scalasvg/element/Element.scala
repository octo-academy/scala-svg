package scalasvg.element

trait Element[+Content] {
  def content: Content
}
