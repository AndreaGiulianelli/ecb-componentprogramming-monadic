package control

import boundary.BoundaryModule
import entity.{EnvModule, State}
import monix.eval.Task
import monix.execution.Scheduler

object EngineModule:
  trait Engine:
    def init(maxNumber: Int): Unit
    def startSimulation(): Unit
  trait Provider:
    val engine: Engine
  type Requirements = BoundaryModule.Provider with EnvModule.Provider
  trait Component:
    context: Requirements =>
    class EngineImpl extends Engine:
      import EngineImpl.given

      private var max: Int = 0 // todo: brutto forse?
      override def init(maxNumber: Int): Unit =
        max = maxNumber
      override def startSimulation(): Unit =
        given Scheduler = monix.execution.Scheduler.global
        simulationStep()
          .loopForever
          .runAsyncAndForget

      private def simulationStep(): Task[Unit] =
        for
          currentState <- getCurrentState()
          newState <- computeNewState(currentState)
          _ <- updateEnv(newState)
          render <- renderBoundaries(newState)
        yield ()

      private def getCurrentState(): Task[State] =
        Task.eval(context.env.getState())

      private def computeNewState(state: State): Task[State] = state match
        case State(number) if number < max => State(number + 1)
        case _ => State(0)

      private def updateEnv(state: State): Task[Unit] =
        Task.eval(context.env.updateState(state))

      private def renderBoundaries(state: State): Task[Seq[Unit]] =
        Task.sequence(context.boundaries.map(_.render(state.number)))


    object EngineImpl:
      given Conversion[State, Task[State]] = Task(_)

  trait Interface extends Provider with Component:
    self: Requirements =>
