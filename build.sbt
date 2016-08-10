
lazy val Vers = new {
  val circe = "0.4.1"
  val scalatest = "2.2.6"
}

lazy val commonSettings = Seq(
  name := "Argus",
  organization := "com.github.aishfenton",
  version := "0.0.1",
  scalaVersion := "2.11.8",
  scalacOptions += "-target:jvm-1.7",
  crossScalaVersions := Seq("2.10.6", "2.11.8"),
  homepage := Some(url("https://github.com/aishfenton/Argus")),
  licenses := Seq("MIT License" -> url("http://www.opensource.org/licenses/MIT")),

  addCompilerPlugin("org.scalamacros" %% "paradise" % "2.1.0" cross CrossVersion.full),
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  publishArtifact in Test := false,
  pomIncludeRepository := { _ => false },
  pomExtra := (
    <scm>
      <url>git@github.com:aishfenton/Argus.git</url>
      <connection>scm:git:git@github.com:aishfenton/Argus.git</connection>
    </scm>
      <developers>
        <developer>
          <id>aishfenton</id>
          <name>Aish Fenton</name>
        </developer>
        <developer>
          <id>datamusing</id>
          <name>Sudeep Das</name>
        </developer>
        <developer>
          <id>dbtsai</id>
          <name>DB Tsai</name>
        </developer>
      </developers>
    )

)

lazy val noPublishSettings = Seq(
  publish := (),
  publishLocal := (),
  publishArtifact := false
)

lazy val argus = project.
  settings(moduleName := "argus").
  settings(commonSettings: _*).
  settings(
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-reflect" % scalaVersion.value,
      "org.scala-lang" % "scala-compiler" % scalaVersion.value % "provided",
      "org.typelevel" %% "macro-compat" % "1.1.1",
//      "com.chuusai" %% "shapeless" % "2.3.1",

      "io.circe" %% "circe-core" % Vers.circe,
      "io.circe" %% "circe-generic" % Vers.circe,
      "io.circe" %% "circe-parser" % Vers.circe,

      "com.lihaoyi" %% "pprint" % "0.4.1",
      "org.scalactic" %% "scalactic" % Vers.scalatest % "test",
      "org.scalatest" %% "scalatest" % Vers.scalatest % "test"
    )
  )

lazy val root = (project in file(".")).
  aggregate(argus).
  settings(commonSettings: _*).
  settings(noPublishSettings: _*)


// Clears screen between refreshes in continuous mode
maxErrors := 5
triggeredMessage := Watched.clearWhenTriggered

