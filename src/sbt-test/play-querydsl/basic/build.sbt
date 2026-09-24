lazy val root = (project in file(".")).enablePlugins(QueryDSLPlugin)

// Play layout without Play: sources live under app/
sourceDirectory in Compile := baseDirectory.value / "app"

javaSource in Compile := baseDirectory.value / "app"

crossPaths := false

libraryDependencies += "org.hibernate.javax.persistence" % "hibernate-jpa-2.0-api" % "1.0.1.Final"

TaskKey[Unit]("check") := {
    val source = IO.read(baseDirectory.value / "target/src_managed/main/querydsl/models/QBook.java")
    if (!source.contains("isbn")) sys.error("QBook was not regenerated after Book changed")
}
