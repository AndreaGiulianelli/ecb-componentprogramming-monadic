package boundary

import java.awt.event.ActionEvent
import javax.swing.{JButton, JFrame, SwingUtilities}
import monix.eval.Task
import ViewUtils.*
import boundary.component.MonadButton
import component.Events.Event
import component.Events.Event.*
import monix.reactive.Observable

class GUI(width: Int = 200, height: Int = 150, title: String = "Test"):

  private lazy val container: Task[JFrame] =
    for
      frame <- io(JFrame(title))
      _ <- io(frame.setSize(100,100))
    yield frame

  private lazy val renderBtns: Seq[MonadButton] =
    Seq(
      MonadButton("click me!", Hit(100))
    )

  def events(): Observable[Event] = Observable
    .fromIterable(renderBtns)
    .flatMap(_.events)

  def init(): Task[Unit] =
    for
      frame <- container.asyncBoundary(swingScheduler)
      _ <- io(renderBtns.map(_.button).foreach(frame.add))
      _ <- io(frame.setVisible(true))
    yield ()

  def render(i: Int): Task[Unit] = Task {
    SwingUtilities.invokeLater { () =>
      println(s"render - $i")
    }
  }
