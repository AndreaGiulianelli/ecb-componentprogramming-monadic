ThisBuild / scalaVersion := "3.1.1"

lazy val root = (project in file("."))
  .settings(
    name := "ecb-componentprogramming-monadic",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.2.12" % Test
    )
  )
