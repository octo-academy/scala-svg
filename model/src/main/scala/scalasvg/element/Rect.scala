package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling, ConditionalProcessing}
import scalasvg.element.category.Category

/**
  * The <rect> element is a basic SVG shape that draws rectangles, defined by their position, width, and height. 
  * The rectangles may have their corners rounded.
  * @param x The x coordinate of the rect.
  * @param y The y coordinate of the rect.
  * @param width The width of the rect.
  * @param height The height of the rect.
  * @param rx The horizontal corner radius of the rect. Defaults to ry if it is specified.
  * @param ry The vertical corner radius of the rect. Defaults to rx if it is specified.
  * @param pathLength The total length of the rectangle's perimeter, in user units.
  */
final case class Rect(x: String, y: String, width: String, height: String, rx: String, ry: String, pathLength: Option[Number]=None, 
    attributes: Seq[Rect.Attribute])
    extends Element.Empty
    with Category.BasicShape

object Rect {

  /**
    * @todo: Extend with all attributes of categories Global Event, Graphical Event, Presentation, Aria
    */
  type Attribute = Core.Id | Core.Tabindex | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
}
