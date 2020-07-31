package scalasvg.model.element

import scalasvg.model.attribute.generic.{ Core, Styling } 
import scalasvg.model.category.Category.Descriptive

final case class Desc(attributes: Seq[Desc.Attribute], content: String) extends Descriptive

object Desc {
  /**
   * @todo: Extend with all attributes of categories Global Event and Document Event
   */ 
  type Attribute = Core.Id | Styling.Class | Styling.Style
}
