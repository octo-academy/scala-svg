package scalasvg.model.element

import scalasvg.model.attribute.Attribute
import scalasvg.model.category.Category.Descriptive

final case class Desc(attributes: Seq[Attribute], content: String) extends Descriptive
