package boundary

import boundary.BoundaryModule.Boundary
import monix.reactive.Observable
import monix.eval.Task

object GUIModule:
  trait Provider:
    val gui: Boundary
  trait Component:
    class GUIBoundaryImpl extends Boundary:
      private val gui = GUI()
      override def init() = Task{println("init")}
      override def render(i: Int) = Task{gui.render(i)}
      override def events(): Observable[String] = ???
  trait Interface extends Provider with Component

