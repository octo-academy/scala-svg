package scalasvg.attribute.generic

import scalasvg.attribute.Attribute
import scalasvg.attribute.category.Category

enum Styling[T] extends Attribute with Category.Generic.Styling {
  override type Value = T

  case Class(override val value: String) extends Styling[String]

  case Style(override val value: String) extends Styling[String]
}
