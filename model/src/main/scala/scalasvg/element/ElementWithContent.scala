
package scalasvg.element



trait ElementWithContent[+Content] extends Element {
  def content: Content
}
