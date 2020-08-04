package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling, ConditionalProcessing}
import scalasvg.element.category.Category

/**
  * The <polygon> element defines a closed shape consisting of a set of connected straight line segments. 
  * The last point is connected to the first point.
  * @param points This attribute defines the list of points (pairs of x,y absolute coordinates) 
  * required to draw the polygon.
  * @param pathLength This attribute lets specify the total length for the path, in user units.
  */
final case class Polygon(points: List[(String,String)], pathLength: Option[Number]=None, attributes: Seq[Ploygon.Attribute])
    extends Element.Empty
    with Category.BasicShape

object Ploygon {

  /**
    * @todo: Extend with all attributes of categories Global Event, Graphical Event, Presentation, Aria
    */
  type Attribute = Core.Id | Core.Tabindex | Styling.Class | Styling.Style | ConditionalProcessing.SystemLanguage
}
