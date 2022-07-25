package boundary

import boundary.BoundaryModule.Boundary
import monix.reactive.Observable
import monix.eval.Task

object GUIModule:
  trait Provider:
    val gui: Boundary
  trait Component:
    class GUIBoundaryImpl extends Boundary:
      private val guiSwing = GUI()
      override def init() = guiSwing.init()
      override def render(i: Int) = guiSwing.render(i)
      override def events(): Observable[String] = ???
  trait Interface extends Provider with Component

