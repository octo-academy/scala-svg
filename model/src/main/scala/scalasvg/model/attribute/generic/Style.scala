package scalasvg.model.attribute.generic

import scalasvg.model.attribute.Attribute
import scalasvg.model.attribute.category.Category

enum Styling[T] extends Attribute with Category.Generic.Styling {
  override type Value = T

  case Class(override val value: String) extends Styling[String]

  case Style(override val value: String) extends Styling[String]
}
