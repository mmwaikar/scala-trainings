package com.codionics.day2.maps

import java.util

import scala.collection.mutable
import scala.jdk.CollectionConverters._

object MapEx {

  /* Immutable maps */

  // by default, a scala map is immutable
  val imMap: Map[Int, String] = Map(1 -> "one", 2 -> "two")

  // In Scala, a map is a collection of pairs. A pair is simply a grouping of
  // two values, not necessarily of the same type, such as ("Alice", 10).
  // The -> operator makes a pair. The value of "Alice" -> 10 is ("Alice", 10)

  // You could have equally well defined the map as
  val imSimilarMap: Map[Int, String] = Map((1, "one"), (2, "two"))

  // The -> operator is just a little easier on the eyes than the parentheses.
  // It also supports the intuition that a map data structure is a kind of
  // function that maps keys to values.

  /* Mutable maps */

  val wcCenturies: mutable.Map[String, Int] = mutable.Map("Rohit" -> 5, "Sangakkara" -> 4, "Chahal" -> 1)

  // In Scala, the analogy between functions and maps is particularly close
  // because you use the () notation to look up key values.
  val rohitsCenturies = wcCenturies("Rohit")

  // BAD - If the map doesn’t contain a value for the requested key, an
  // exception is thrown. To check whether there is a key with the given
  // value, call the contains method:
  val safeRohitsCenturies = if (wcCenturies.contains("Rohit")) wcCenturies("Rohit") else 0

  // or we can use the shortcut
  val simplerSafeRohitsCenturies = wcCenturies.getOrElse("Rohit", 0)

  val optionalRohitsCenturies: Option[Int] = wcCenturies.get("Rohit")

  // updating a value in a mutable map
  wcCenturies("Chahal") = 0

  // remove a key value pair
  wcCenturies -= "Chahal"

  // use += to add multiple key-value pairs
  wcCenturies += ("Rahul" -> 1, "Shikhar" -> 1)

  val newImMap = imMap + (3 -> "three", 4 -> "four")

  /* Iterating over maps */

  for ((k, v) <- imMap)
    println(s"k: $k, v: $v")

  // or
  imMap.foreach { case(k, v) => println(s"k: $k, v: $v") }

  val keys: Iterable[Int] = imMap.keys
  val values: Seq[String] = imMap.values.toSeq

  // to convert a Scala map to a Java map
  val javaMap: util.Map[Int, String] = imMap.asJava

  // to convert a java map to scala
  val scalaMap: mutable.Map[Int, String] = javaMap.asScala
  val scalaImMap: Map[Int, String] = javaMap.asScala.toMap

  /* Tuples */

  // Pairs are the simplest case of tuples—aggregates of values of different types.
  val cityZipTuple: (String, Int) = ("Pune", 411038)

  // Unlike array or string positions, the component positions of a tuple start with 1, not 0.
  val city: String = cityZipTuple._1
  val zip: Int = cityZipTuple._2

  // usually, it is better to use pattern matching to get at the components of a tuple.
  // in Clojure, this is called destructuring
  val (c, z) = cityZipTuple
  println(s"city: $c, zip: $z")

  val cityZipStateTuple = ("Pune", 411038, "MH")
  val state = cityZipStateTuple._3

  // we can use an _ if you don’t need all components:
  val (c1, z1, _) = cityZipStateTuple
}
