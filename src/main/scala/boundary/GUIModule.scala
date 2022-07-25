package boundary

import boundary.BoundaryModule.Boundary
import monix.reactive.Observable
import monix.eval.Task
import component.Events.Event

object GUIModule:
  trait Provider:
    val gui: Boundary
  trait Component:
    class GUIBoundaryImpl extends Boundary:
      private val guiSwing = GUI()
      override def init() = guiSwing.init()
      override def render(i: Int) = guiSwing.render(i)
      override def events(): Observable[Event] = guiSwing.events()
  trait Interface extends Provider with Component

