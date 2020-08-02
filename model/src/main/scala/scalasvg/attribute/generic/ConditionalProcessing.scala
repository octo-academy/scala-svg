package scalasvg.attribute.generic

import scalasvg.attribute.Attribute
import scalasvg.attribute.category.Category

enum ConditionalProcessing[T] extends Attribute with Category.Generic.ConditionalProcessing {
  override type Value = T

    /**
   * The systemLanguage attribute represents a list of supported language tags. 
   * This list is matched against the language defined in the user preferences.
   */
  case SystemLanguage(override val value: String) extends ConditionalProcessing[String]

}
