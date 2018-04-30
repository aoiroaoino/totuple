organization := "com.github.aoiroaoino"

name := "case-class-2-tuple"

scalaVersion := "2.12.5"

crossScalaVersions := Seq("2.11.11", "2.12.5")

version := "0.1.0-SNAPSHOT"

libraryDependencies += "org.scala-lang" % "scala-compiler" % scalaVersion.value

scalacOptions in Test ++= {
  val jar = (packageBin in Compile).value
  println(jar)
  println(jar.lastModified)
  println(jar.getAbsolutePath)
  Seq(
    "-Yrangepos",
    s"-Xplugin:${jar.getAbsolutePath}",
    s"-JlastModified=${jar.lastModified}"
  )
}
