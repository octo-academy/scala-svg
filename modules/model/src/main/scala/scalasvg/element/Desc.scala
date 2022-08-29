package scalasvg.element

import scalasvg.attribute.generic.{ Core, Styling }
import scalasvg.element.category.Category

/**
 * The <desc> element provides an accessible, long-text description of any SVG
 * container element or graphics element.
 */
final case class Desc(attributes: Seq[Desc.Attribute] = Seq.empty, content: Seq[Desc.Content])
    extends Element[Seq[Desc.Content]](content)
      with Category.Descriptive

object Desc {

  /**
   * @todo:
   *   Extend with all attributes of categories Global Event and Document Event
   */
  type Attribute = Core.Id | Styling.Class | Styling.Style
  type Content   = Plain | Element[Any]
}
