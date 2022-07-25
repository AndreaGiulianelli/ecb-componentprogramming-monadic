package control

import boundary.BoundaryModule
import boundary.GUIModule
import entity.EnvModule

object Launch
  extends BoundaryModule.Interface
  with LauncherModule.Interface
  with LoaderModule.Interface
  with EngineModule.Interface
  with EnvModule.Interface
  with GUIModule.Interface:

  override val gui = GUIBoundaryImpl()
  override val boundaries = Seq(gui)
  override val env = EnvImpl()
  override val engine = EngineImpl()
  override val loader = LoaderImpl()
  override val launcher = LauncherImpl()

  @main def main(): Unit = launcher.launch()

