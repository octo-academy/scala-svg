package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling, ConditionalProcessing}
import scalasvg.element.category.Category

/**
  * The <line> element is an SVG basic shape used to create a line connecting two points.
  * @param x1 Defines the x-axis coordinate of the line starting point.
  * @param x2 Defines the x-axis coordinate of the line ending point.
  * @param y1 Defines the y-axis coordinate of the line starting point.
  * @param y2 Defines the y-axis coordinate of the line ending point.
  * @param pathLength Defines the total path length in user units.
  */
final case class Line(x1: String, x2: String, y1: String, y2: String, pathLength: Number)(attributes: Seq[Line.Attribute])
    extends Element.Empty
    with Category.BasicShape

object Line {

  /**
    * @todo: Extend with all attributes of categories Global Event, Graphical Event, Presentation, Aria
    */
  type Attribute = Core.Id | Core.Tabindex | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
}