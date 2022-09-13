package scalasvg.dsl

import scalasvg.element.Element

import scala.collection.mutable.ListBuffer

package object internal {
  type Initializer[T] = ListBuffer[T] ?=> Unit

  private[dsl] def initialize[C, E <: Element[Seq[C]]](construct: Seq[C] => E, init: Initializer[C])(using
    parent:                                                       ListBuffer[_ >: E]
  ): E = initializeParent(initializeChildren(construct, init))

  private[dsl] def initializeChildren[C, E <: Element[Seq[C]]](construct: Seq[C] => E, init: Initializer[C]): E = {
    val children = ListBuffer.empty[C]
    init(using children)
    construct(children.toSeq)
  }

  private[dsl] def initializeParent[E <: Element[_]](element: E)(using parent: ListBuffer[_ >: E]): E = {
    parent.addOne(element)
    element
  }

}
