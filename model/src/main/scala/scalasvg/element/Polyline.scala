package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling, ConditionalProcessing}
import scalasvg.element.category.Category

/**
  * The <polyline> SVG element is an SVG basic shape that creates straight lines connecting several points. 
  * Typically a polyline is used to create open shapes as the last point doesn't have to be connected 
  * to the first point. For closed shapes see the <polygon> element.
  * @param points This attribute defines the list of points (pairs of x,y absolute coordinates) 
  * required to draw the polyline.
  * @param pathLength This attribute lets specify the total length for the path, in user units.
  */
final case class Polyline(points: List[(String,String)], pathLength: Option[Number] = None, attributes: Seq[Polyline.Attribute], content: Seq[Polyline.Content])
    extends Element[Seq[Polyline.Content]](content)
    with Category.BasicShape with Category.Graphics with Category.Shape

object Polyline {

  /**
    * @todo: Extend with all attributes of categories Global Event, Graphical Event, Presentation, Aria
    */
  type Attribute = Core.Id | Core.Tabindex | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
  type Content = Category.Descriptive | Category.Animation
}
