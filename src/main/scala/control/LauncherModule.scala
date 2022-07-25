package control

import boundary.BoundaryModule

object LauncherModule:
  trait Launcher:
    def launch(): Unit
  trait Provider:
    val launcher: Launcher
  type Requirements = BoundaryModule.Provider with LoaderModule.Provider
  trait Component:
    context: Requirements =>
    class LauncherImpl extends Launcher:
      override def launch(): Unit =
        context.boundaries.foreach(_.init())
        context.loader.load(10)
  trait Interface extends Provider with Component:
    self: Requirements =>