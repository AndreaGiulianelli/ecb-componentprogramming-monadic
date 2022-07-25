package boundary.component

import monix.eval.Task
import monix.reactive.{Observable, OverflowStrategy}

import javax.swing.JButton
import Events.Event
import monix.execution.Cancelable

import java.awt.event.ActionEvent

trait MonadButton:
  def events: Observable[Event]
  def button: JButton

object MonadButton:
  def apply(title: String, event: Event): MonadButton =
    MonadButtonImpl(JButton(title), event)
  private class MonadButtonImpl(override val button: JButton, event: Event) extends MonadButton:
    override lazy val events: Observable[Event] = Observable.create(OverflowStrategy.Unbounded) { out =>
      button.addActionListener((e: ActionEvent) => out.onNext(event))
      Cancelable.empty
    }




