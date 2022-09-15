package scalasvg.attribute.category

sealed trait Category

object Category {

  sealed trait Generic

  sealed trait Filters extends Category

  sealed trait Animation extends Category

  sealed trait Event extends Category

  trait XLink extends Category

  /**
   * SVG presentation attributes are CSS properties that can be used as
   * attributes on SVG elements.
   *
   * Note: All SVG presentation attributes can be used as CSS properties
   */
  trait Presentation extends Category

  object Generic {

    /**
     * The SVG core attributes are all the common attributes that can be
     * specified on any SVG element.
     */
    trait Core extends Generic

    /**
     * The SVG styling attributes are all the attributes that can be specified
     * on any SVG element to apply CSS styling effects.
     */
    trait Styling extends Generic

    /**
     * The SVG conditional processing attributes are all the attributes that can
     * be specified on some SVG elements to control whether or not the element
     * on which it appears should be rendered.
     */
    trait ConditionalProcessing extends Generic
  }

  object Filter {
    trait Primitive extends Filters

    trait TransferFunction extends Filters
  }

  object Animation {
    trait AttributeTarget extends Animation

    trait AnimationTiming extends Animation

    trait Value extends Animation

    trait Addition extends Animation
  }

  object Event {
    trait Animation extends Event

    trait Document extends Event

    trait Global extends Event

    trait Graphical extends Event
  }

}
