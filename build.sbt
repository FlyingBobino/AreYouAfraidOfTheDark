name := "AreYouAfraidOfTheDarkSBT"

version := "0.1"

scalaVersion := "2.13.6"

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-parallel-collections" % "1.0.3",
  "org.scalactic"          %% "scalactic"                  % "3.2.9",
  "org.scalatest"          %% "scalatest"                  % "3.2.9" % "test"
)

resolvers += "Artima Maven Repository" at "https://repo.artima.com/releases"
