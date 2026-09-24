libraryDependencies += "org.scala-sbt" % "scripted-plugin" % sbtVersion.value

// formatter for the javafmt/javafmtCheck tasks; 1.7 is the last release running on JDK 8
libraryDependencies += "com.google.googlejavaformat" % "google-java-format" % "1.7"
