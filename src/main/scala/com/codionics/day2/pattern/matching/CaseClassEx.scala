package com.codionics.day2.pattern.matching

object CaseClassEx {

  /* Case classes */

  // Case classes are a special kind of classes that are optimized for use in pattern matching.
  // They are immutable and are also called value classes (classes meant for holding immutable data).
  abstract class Amount
  case class Dollar(value: Double) extends Amount
  case class Currency(value: Double, unit: String) extends Amount

  // You can also have case objects for singletons:
  case object Nothing extends Amount

  val amt: Amount = null

  amt match {
    case Dollar(v)      => "$" + v
    case Currency(_, u) => "Oh noes, I got " + u
    case Nothing        => ""
  }

  // When you declare a case class, several things happen automatically.
  // Each of the constructor parameters becomes a val unless it is explicitly
  // declared as a var (which is not recommended).
  // An apply method is provided for the companion object that lets you construct
  // objects without new, such as Dollar(29.95) or Currency(29.95, "EUR").
  // An unapply method is provided that makes pattern matching work.
  // Methods toString, equals, hashCode, and copy are generated unless they are explicitly provided.

  /* Copy method and named parameters */

  // The copy method of a case class makes a new object with the same values as an existing one.
  val amtUsd = Currency(29.95, "EUR")
  val copy = amtUsd.copy()

  // By itself, that isn’t very useful—after all, a Currency object is immutable, and one can just
  // share the object reference. However, you can use named parameters to modify some of the properties:
  val amtInRs = amtUsd.copy(unit = "INR")

  /* Sealed classes */

  // When you use pattern matching with case classes, you would like the compiler to check
  // that you exhausted all alternatives. You achieve this by declaring the common superclass
  // as sealed (so we can make the Amount class above, as sealed). All subclasses of a sealed
  // class must be defined in the same file as the class itself.

  /* Simulating enumerations */

  // Case classes let you simulate enumerated types in Scala.
  sealed abstract class TrafficLightColor
  case object Red extends TrafficLightColor
  case object Yellow extends TrafficLightColor
  case object Green extends TrafficLightColor

  val color: TrafficLightColor = Red
  color match {
    case Red    => "stop"
    case Yellow => "hurry up"
    case Green  => "go"
  }
}
