package scalasvg.attribute.generic

import scalasvg.attribute.Attribute
import scalasvg.attribute.category.Category

import java.util.Locale

enum Core[T] extends Attribute with Category.Generic.Core {
  override type Value = T

  /**
   * The id attribute assigns a unique name to an element.
   */
  case Id(override val value: String) extends Core[String]

  /**
   * The lang attribute specifies the primary language used in contents and
   * attributes containing text content of particular elements.
   */
  case Lang(override val value: Locale) extends Core[Locale]
}
