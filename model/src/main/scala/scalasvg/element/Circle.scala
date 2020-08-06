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
final case class Circle(cx: String, cy: String, r: String, pathLength: Option[Number]=None, attributes: Circle.Attribute*)
    (content: Circle.Content*)
    extends Element[Seq[Circle.Content]](content)
    with Category.BasicShape with Category.Graphics with Category.Shape

object Circle {

  /**
    * @todo: Extend with all attributes of categories Global Event, Graphical Event, Presentation, Aria
    */
  type Attribute = Core.Id | Core.Tabindex | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
  type Content = Category.Descriptive | Category.Animation
}
