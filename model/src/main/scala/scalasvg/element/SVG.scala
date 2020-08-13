package scalasvg.element

import scalasvg.element.category.Category

final case class SVG(viewBox: String, attributes: Seq[SVG.Attribute], content: Seq[SVG.Content])
  extends Element[Seq[SVG.Content]](content)
  with Category.Container
  with Category.Renderable
  with Category.Structural


object SVG {
  type Attribute = scalasvg.attribute.Attribute
  type Content = Category.Animation | Category.Descriptive | Category.Shape | Category.Structural | Category.Gradient
}
