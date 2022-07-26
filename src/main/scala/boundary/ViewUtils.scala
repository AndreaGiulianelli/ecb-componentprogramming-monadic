package boundary

import monix.eval.Task
import monix.execution.Scheduler

import javax.swing.SwingUtilities
import scala.concurrent.ExecutionContext

object ViewUtils:
  // A facade on Task with a terminology more related to UI.
  def io[A](computation: A): Task[A] = Task(computation)
  

  //Scheduler used from shifting the task execution to the AWT thread.
  val swingScheduler: Scheduler = Scheduler.apply(new ExecutionContext {
    override def execute(runnable: Runnable): Unit = SwingUtilities.invokeLater(runnable)
    override def reportFailure(cause: Throwable): Unit = {} // todo: technical debt
  })
