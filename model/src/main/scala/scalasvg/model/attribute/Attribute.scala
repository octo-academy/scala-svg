package scalasvg.model.attribute

trait Attribute {
  type Value
  def value: Value
}
