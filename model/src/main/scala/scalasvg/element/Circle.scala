package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling, ConditionalProcessing}
import scalasvg.element.category.Category

/**
  * The <circle> SVG element is an SVG basic shape, used to draw circles based on a center point and a radius.
  * @param cx The x-axis coordinate of the center of the circle.
  * @param cy The y-axis coordinate of the center of the circle.
  * @param r The radius of the circle. A value lower or equal to zero disables rendering of the circle.
  * @param pathLength The total length for the circle's circumference, in user units.
  */
final case class Circle(cx: String, cy: String, r: String, pathLength: Number)(attributes: Seq[Circle.Attribute])
    extends Element
    with Category.BasicShape

object Circle {
  type Attribute = Core.Id | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
}
