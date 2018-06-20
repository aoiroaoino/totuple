organization := "com.github.aoiroaoino"

name := "totuple"

scalaVersion := "2.12.6"

crossScalaVersions := Seq("2.11.11", "2.12.6")

version := "0.1.1"

libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value

scalacOptions in Test ++= {
  val jar = (packageBin in Compile).value
  Seq(
    "-Yrangepos",
    s"-Xplugin:${jar.getAbsolutePath}",
    s"-JlastModified=${jar.lastModified}"
  )
}

publishTo := sonatypePublishTo.value
