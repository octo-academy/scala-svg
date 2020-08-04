package scalasvg.element

import scalasvg.attribute.generic.{Core, Styling}
import scalasvg.element.category.Category

/**
  * The <desc> element provides an accessible, long-text description of any SVG container element or graphics element.
  */
final case class Desc(attributes: Seq[Desc.Attribute])(content: String)
    extends Element.Container[String](content)
    with Category.Descriptive

object Desc {

  /**
    * @todo: Extend with all attributes of categories Global Event and Document Event
    */
  type Attribute = Core.Id | Styling.Class | Styling.Style
}
