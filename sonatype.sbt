import xerial.sbt.Sonatype._

publishMavenStyle := true

licenses := Seq("MIT License" -> url("https://opensource.org/licenses/mit-license"))

sonatypeProjectHosting := Some(GitHubHosting("aoiroaoino", "totuple", "aoiro.aoino@gmail.com"))

homepage := Some(url("https://github.com/aoiroaoino/totuple"))

scmInfo := Some(
  ScmInfo(
      url("https://github.com/aoiroaoino/totuple"),
      "scm:git@github.com:aoiroaoino/totuple.git"
    )
  )

developers := List(
  Developer(
    id    = "aoiroaoino",
    name  = "Naoki Aoyama",
    email = "aoiro.aoino@gmail.com",
    url   = url("https://github.com/aoiroaoino")
  )
)
