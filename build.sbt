name := "twitter-stack"

version := "1.0"

scalaVersion := "2.11.8"

resolvers += "twttr" at "https://maven.twttr.com/"

libraryDependencies ++= Seq(
  "org.apache.thrift" % "libthrift" % "0.9.3",
  "com.twitter" %% "twitter-server" % "1.26.0" exclude("com.twitter", "libthrift"),
  "com.twitter" %% "scrooge-core" % "4.13.0" exclude("com.twitter", "libthrift")
)

scroogeThriftSourceFolder in Compile <<= baseDirectory {
      base => base / "src/main/resources"
    }

