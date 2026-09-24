resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.3.9")

addSbtPlugin("com.pedrozc90.play" % "play-querydsl" % sys.props("plugin.version"))
