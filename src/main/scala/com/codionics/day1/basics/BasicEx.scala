package com.codionics.day1.basics

import scala.math._

object BasicEx {

  // a mutable variable
  var i = 0

  // which can be updated later
  i = 2

  // however, an immutable value
  val name = "Boris"

  // cannot be updated, once it is initialized (the below line is not allowed)
  // name = "Andy"

  // notice, you don't have to say String name = "something"
  // also notice that the compiler is smart enough to infer the type of the variables / values

  // set both values to 100
  val x, y = 100

  val one = 1.toString

  // Scala has seven numeric types: Byte, Char, Short, Int, Long, Float, and Double, and a Boolean type.
  // However, unlike Java, these types are classes.
  // Similarly, there are classes RichInt, RichDouble, RichChar, and so on.

  // In Scala, you use methods, not casts, to convert between numeric types.
  val ninetyNine = 99.44.toInt
  val c = 99.toChar

  // operators do their usual job
  val sum = 3 + 5

  // which is same as
  val sum1 = 3.+(5)

  // In general, you can write a method b as a shorthand for a.method(b)

  // Scala has functions in addition to methods. It is simpler to use mathematical functions such as min or pow in Scala than in Java.
  val sqrtOf2 = sqrt(2)
  val minOf2 = min(3, Pi)

  // In Scala, it is common to use a syntax that looks like a function call.
  val e = "Hello"(1)

  // You can think of this as an overloaded form of the () operator. It is implemented as a method with the name apply.
  // For example, in the documentation of the StringOps class, you will find a method def apply(n: Int): Char
  // That is, "Hello"(4) is a shortcut for "Hello".apply(4)
  // We will encounter this method with relation to case class as well.
}
