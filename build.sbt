name := "spark-scala-exercise"
description := "Spark Scala Exercise"
version := "1.0"
scalaVersion := "2.11.12"

val sparkVersion        = "2.4.5"
val scalaTestVersion    = "3.0.1"

libraryDependencies ++= Seq(
  "org.apache.spark"  %% "spark-core" % sparkVersion,
  "org.apache.spark"  %% "spark-sql"  % sparkVersion,
  "org.scalatest" %% "scalatest"  % scalaTestVersion  % "test"
)

mainClass in (Compile, packageBin) := Some("main.scala.exercise.Driver")

// constant jar name
assemblyJarName in assembly := s"spark_exercise.jar"

// Merge strategy for assembling conflicts
assemblyMergeStrategy in assembly := {
  case PathList("reference.conf") => MergeStrategy.concat
  case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}
