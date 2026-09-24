# play-querydsl

sbt plugin that generates [QueryDSL](http://querydsl.com) `Q*` classes for JPA entities in [Play](https://www.playframework.com) Java projects.

| Plugin | sbt  | Play  | JDK |
| ------ | ---- | ----- | --- |
| 1.x    | 0.13 | 2.3.x | 8   |

> Originally forked from [CedricGatay/play-querydsl](https://github.com/CedricGatay/play-querydsl).

## Installation

The plugin is not on Maven Central. It is published to a Maven repository hosted on GitHub Pages, so add it as a resolver (no credentials needed).

`project/plugins.sbt`:

```scala
resolvers += "pedrozc90" at "https://pedrozc90.github.io/play-querydsl/"

addSbtPlugin("com.pedrozc90.play" % "play-querydsl" % "1.0.0")
```

`build.sbt`:

```scala
lazy val root = (project in file(".")).enablePlugins(PlayJava, QueryDSLPlugin)
```

Use JPA for your entities; Ebean is not supported.

## Settings

| Setting             | Default                  | Description                                                                                |
| ------------------- | ------------------------ | ------------------------------------------------------------------------------------------ |
| `queryDSLPackage`   | `models`                 | Source path under `app/` to scan, e.g. `"models/included"`                                 |
| `queryDSLIncludes`  | empty (all)              | Globs relative to `queryDSLPackage` of sources to process                                  |
| `queryDSLVersion`   | `4.4.0`                  | QueryDSL version for `querydsl-apt` and `querydsl-jpa`, 3.x or 4.x                         |
| `queryDSLProcessor` | JPA processor of version | Annotation processor class, e.g. `com.querydsl.apt.hibernate.HibernateAnnotationProcessor` |
| `queryDSLOptions`   | empty                    | Extra javac options, e.g. `Seq("-Aquerydsl.entityAccessors=true")`                         |

Includes use [Java glob syntax](https://docs.oracle.com/javase/8/docs/api/java/nio/file/FileSystem.html#getPathMatcher-java.lang.String-).
When `queryDSLIncludes` is empty, every source under `queryDSLPackage` is processed:

```scala
queryDSLPackage := "models"
queryDSLIncludes := Seq("included/**", "shared/*Entity.java")
```

`included/**` matches `included/Book.java`, but `included/**/*.java` only matches files in subdirectories.
Entities referenced by a processed entity must be processed too, otherwise its `Q*` class does not compile.
To skip a single entity, annotate it with `@QueryExclude`.

### Multiple `models` folders

Only one directory is scanned, so point `queryDSLPackage` at the common parent of the `models` folders and select them with `queryDSLIncludes`:

```
app/
  pedrozc90/
    billing/models/Invoice.java
    users/models/User.java
    users/controllers/Users.java
```

```scala
queryDSLPackage := "pedrozc90"                        // "" scans all of app/
queryDSLIncludes := Seq("**/models/**", "models/**")
```

`**/models/**` needs at least one directory before `models`, so `models/**` is needed for a `models` folder directly under `queryDSLPackage`.
Matching is case-sensitive.

## Tasks

`Q*` sources are generated on `compile`. `sbt queryDSLGenerate` generates them into `target/.../src_managed/main/querydsl` without compiling.

## Usage

The major version of `queryDSLVersion` selects the artifacts and the annotation processor; the Java API differs between the two.

### QueryDSL 4.x

`build.sbt`:

```scala
queryDSLVersion := "4.4.0"
```

Artifacts come from `com.querydsl` and classes live under `com.querydsl.*`:

```java
import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.jpa.impl.JPAQuery;

List<Book> books = new JPAQuery<Book>(JPA.em())
    .from(QBook.book)
    .where(QBook.book.title.contains("ipsum"))
    .fetch();
```

### QueryDSL 3.x

`build.sbt`:

```scala
queryDSLVersion := "3.6.3"
```

Artifacts come from `com.mysema.querydsl` and classes live under `com.mysema.query.*`:

```java
import com.mysema.query.annotations.QueryProjection;
import com.mysema.query.jpa.impl.JPAQuery;

List<Book> books = new JPAQuery(JPA.em())
    .from(QBook.book)
    .where(QBook.book.title.contains("ipsum"))
    .list(QBook.book);
```

## Annotation processors

The `Q*` classes are generated in a separate pass that runs only the QueryDSL processor, so other annotation processors on the compile classpath, such as Lombok and MapStruct, keep working (see `src/sbt-test/play-querydsl/querydsl4`).

## Development

Requires [mise](https://mise.jdx.dev), which installs JDK 8, sbt and scalafmt.

```sh
make install        # install toolchain
make test           # run the scripted tests (src/sbt-test), including a Play app per QueryDSL major version
make fmt            # format Scala, sbt and Java sources (make fmt-check to verify)
make publish-local  # publish the plugin to the local ivy repository
```

Pushing a `v*` tag runs `.github/workflows/release.yml`, which tests, publishes to the `gh-pages` branch (served by GitHub Pages) and creates a GitHub release.

## License

MIT, see [LICENSE](LICENSE).
