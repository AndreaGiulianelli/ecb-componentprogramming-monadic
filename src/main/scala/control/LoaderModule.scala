package control

object LoaderModule:
  trait Loader:
    def load(configMax: Int): Unit
  trait Provider:
    val loader: Loader
  type Requirements = EngineModule.Provider
  trait Component:
    context: Requirements =>
    class LoaderImpl extends Loader:
      override def load(configMax: Int): Unit =
        context.engine.init(configMax)
        context.engine.startSimulation()
  trait Interface extends Provider with Component:
    self: Requirements =>

