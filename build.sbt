name := "SimpleSearchChallenge"

version := "0.1"

scalaVersion := "2.12.14"

import Dependencies._

ThisBuild / scalaVersion     := "2.12.14"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.eduardsimon"
ThisBuild / organizationName := "eduardsimon"

lazy val root = (project in file("."))
  .settings(
    name := "SimpleSearchChallenge",
    libraryDependencies ++= {
      Seq(
        scalaTest % Test
      )
    }
  )