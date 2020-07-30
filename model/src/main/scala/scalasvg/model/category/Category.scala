package scalasvg.model.category

sealed trait Category
object Category {
  trait Animation         extends Category
  trait BasicShape        extends Category
  trait Container         extends Category
  trait Descriptive       extends Category
  trait Filter            extends Category
  trait Font              extends Category
  trait Gradient          extends Category
  trait Graphics          extends Category
  trait GraphicsReference extends Category
  trait LightSource       extends Category
  trait NeverRendered     extends Category
  trait PaintServer       extends Category
  trait Renderable        extends Category
  trait Shape             extends Category
  trait Structural        extends Category
  trait TextContent       extends Category
  trait TextContentChild  extends Category
  trait Uncategorized     extends Category
}
