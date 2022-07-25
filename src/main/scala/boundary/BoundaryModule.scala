package boundary
import monix.eval.Task
import monix.reactive.Observable

object BoundaryModule:
  trait Boundary:
    def init(): Task[Unit]
    def render(i: Int): Task[Unit]
    def events(): Observable[String]
  trait Provider:
      val boundaries: Seq[Boundary]
  trait Interface extends Provider
