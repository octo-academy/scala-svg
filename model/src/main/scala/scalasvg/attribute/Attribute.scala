package scalasvg.attribute

trait Attribute {
  type Value
  def value: Value
}
