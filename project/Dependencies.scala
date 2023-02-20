import sbt._

object Dependencies {

  lazy val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.2.0"
  
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.8"

  lazy val hibernate = "org.hibernate.javax.persistence" % "hibernate-jpa-2.1-api" % "1.0.2"
  lazy val jUnit = "junit" % "junit" % "4.12"
}
