name := "PubSubHighThroughputTestAlpakka"

version := "0.1"

scalaVersion := "2.12.7"

libraryDependencies ++= Seq(
  "com.lightbend.akka" %% "akka-stream-alpakka-google-cloud-pub-sub" % "1.0.2"
  ,"com.typesafe.akka" %% "akka-actor"                               % "2.5.23"
  ,"com.typesafe.akka" %% "akka-stream"                              % "2.5.23"
  ,"com.typesafe.akka" %% "akka-protobuf"                            % "2.5.23"
  ,"com.typesafe.akka" %% "akka-discovery"                           % "2.5.23"
  ,"com.typesafe"      % "config"                                    % "1.3.4"
  ,"com.google.auth"   % "google-auth-library-oauth2-http"           % "0.15.0"
  //,"io.grpc" % "grpc-netty-shaded" % "1.20.0"
  //,"com.google.auth" % "google-auth-library-oauth2-http" % "0.15.0"
  
  //, "io.netty" % "netty-tcnative-boringssl-static" % "2.0.20.Final"
  //,"io.netty" % "netty-tcnative-boringssl-static" % "2.0.25.Final"
)
