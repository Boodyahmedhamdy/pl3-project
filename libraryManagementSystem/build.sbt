ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.11"

// akka version
val akkaVersion = "2.6.18"



lazy val root = (project in file("."))
  .settings(
    name := "libraryManagementSystem",
    libraryDependencies ++= Seq(
      // Akka Actor
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      // Akka Logging with SLF4J
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      // Akka Test Kit (optional for testing)
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",

      "com.typesafe.slick" %% "slick" % "3.4.1",
      "com.typesafe.slick" %% "slick-hikaricp" % "3.4.1",
      "com.mysql" % "mysql-connector-j" % "8.0.33",
      "org.slf4j" % "slf4j-nop" % "2.0.5",
    )
  )
