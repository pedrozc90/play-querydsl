name := "querydsl3"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.12"

lazy val root = (project in file(".")).enablePlugins(PlayJava, QueryDSLPlugin)

// scripted reserves the "test" file for its script, so tests live in tests/
sourceDirectory in Test := baseDirectory.value / "tests"

javaSource in Test := baseDirectory.value / "tests"

queryDSLPackage := "pedrozc90/models"

queryDSLIncludes := Seq("included/**")

queryDSLVersion := "3.6.3"

libraryDependencies ++= Seq(
    javaJpa,
    "org.hibernate" % "hibernate-entitymanager" % "4.2.21.Final",
    "org.mapstruct" % "mapstruct" % "1.5.5.Final",
    // annotation processors, discovered by javac from the compile classpath
    "org.projectlombok" % "lombok" % "1.18.30" % "provided",
    "org.mapstruct" % "mapstruct-processor" % "1.5.5.Final" % "provided",
    "org.projectlombok" % "lombok-mapstruct-binding" % "0.2.0" % "provided"
)
