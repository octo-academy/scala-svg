package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling, ConditionalProcessing}
import scalasvg.element.category.Category

/**
  * The <ellipse> element is an SVG basic shape, used to create ellipses based on a center coordinate, 
  * and both their x and y radius.
  * @param cx The x position of the ellipse.
  * @param cy The y position of the ellipse.
  * @param rx The radius of the ellipse on the x axis.
  * @param ry The radius of the ellipse on the y axis.
  * @param pathLength This attribute lets specify the total length for the path, in user units.
  */
final case class Ellipse(cx: String, cy: String, rx: String, ry: String, pathLength: Option[Number]=None, attributes: Ellipse.Attribute*)
    (content: Ellipse.Content*)
    extends Element[Seq[Ellipse.Content]](content)
    with Category.BasicShape with Category.Graphics with Category.Shape

object Ellipse {
  
  /**
    * @todo: Extend with all attributes of categories Global Event, Graphical Event, Presentation, Aria
    */  
  type Attribute = Core.Id | Core.Tabindex | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
  type Content = Category.Descriptive | Category.Animation
}
