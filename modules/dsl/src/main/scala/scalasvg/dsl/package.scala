package scalasvg

import scalasvg.attribute.Attribute
import scalasvg.dsl.internal.{ Initializer, initialize, initializeChildren, initializeParent }
import scalasvg.element.{ Circle, Desc, Plain, SVG }

import scala.collection.mutable.ListBuffer

package object dsl {

  def svg(viewBox: String, attributes: SVG.Attribute*)(init: Initializer[SVG.Content]): SVG =
    initializeChildren(SVG(viewBox, attributes, _), init)

  def circle(x:   String, y: String, r: String, pathLength: Option[Number] = None, attributes: Circle.Attribute*)(
    init:         Initializer[Circle.Content]
  )(using parent: ListBuffer[_ >: Circle]): Circle = initialize(Circle(x, y, r, pathLength, attributes, _), init)

  def desc(attributes: Desc.Attribute*)(init: Initializer[Desc.Content])(using parent: ListBuffer[_ >: Desc]): Desc =
    initialize(Desc(attributes, _), init)

  def plain(text: String)(using parent: ListBuffer[_ >: Plain]): Plain = initializeParent(Plain(text))
}
