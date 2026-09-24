import com.google.googlejavaformat.java.{Formatter, JavaFormatterOptions}

name := "play-querydsl"

organization := "com.pedrozc90.play"

version := "1.0.0"

sbtPlugin := true

publishMavenStyle := true

// maven repository served by GitHub Pages; the release workflow clones the gh-pages branch here before publishing
publishTo := Some(Resolver.file("gh-pages", target.value / "gh-pages"))

pomExtra := (
    <url>https://github.com/pedrozc90/play-querydsl</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>https://opensource.org/licenses/MIT</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:pedrozc90/play-querydsl.git</url>
      <connection>scm:git:git@github.com:pedrozc90/play-querydsl.git</connection>
    </scm>
    <developers>
      <developer>
        <id>pedrozc90</id>
        <name>Pedro</name>
        <url>https://github.com/pedrozc90/play-querydsl.git</url>
      </developer>
    </developers>
)

ScriptedPlugin.scriptedSettings

scriptedLaunchOpts ++= Seq("-Xmx1024M", "-Dplugin.version=" + version.value)

scriptedBufferLog := false

lazy val javafmt = taskKey[Unit]("Format the scripted tests' Java sources (google-java-format, AOSP style)")
lazy val javafmtCheck = taskKey[Unit]("Fail if the scripted tests' Java sources are not formatted")

def javaFormatter = new Formatter(JavaFormatterOptions.builder().style(JavaFormatterOptions.Style.AOSP).build())

// skips target/, so generated Q*.java are left alone; returns (file, formatted) for files that differ
def unformattedJava(dir: File): Seq[(File, String)] = {
    val formatter = javaFormatter
    (dir ** "*.java").get.filterNot(_.getPath.contains("/target/")).flatMap { f =>
        val source = IO.read(f)
        val formatted = formatter.formatSource(source)
        if (formatted == source) None else Some(f -> formatted)
    }
}

javafmt := {
    unformattedJava(sbtTestDirectory.value).foreach { case (f, formatted) =>
        IO.write(f, formatted)
        streams.value.log.info(s"Formatted $f")
    }
}

javafmtCheck := {
    val unformatted = unformattedJava(sbtTestDirectory.value).map(_._1)
    unformatted.foreach(f => streams.value.log.error(s"Not formatted: $f"))
    if (unformatted.nonEmpty) sys.error(s"${unformatted.size} Java file(s) not formatted, run `sbt javafmt`")
}
