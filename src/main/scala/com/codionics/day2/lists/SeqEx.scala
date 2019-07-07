package com.codionics.day2.lists

object SeqEx {

  val seq = Seq(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

  val firstElement = seq.head

  val remainingElements = seq.tail

  val firstNElements = seq.take(3)

  val allExceptFirstNs = seq.drop(3)

  val allExceptLastNs = seq.dropRight(3)

  // double every element of the list
  val doubled: Seq[Int] = seq.map(_ * 2)

  /**
    * By default, collections in Scala are immutable - meaning, they can't
    * be changed once they are created.
    *
    * So after we double every element, doubled is a new sequence with every
    * element doubled, but the original seq is unchanged.
    */

  // here, x => x % 2 == 0 is an anonymous function
  // it is a predicate, which is a function which returns a boolean
  val evens = seq.filter(x => x % 2 == 0)

  // we can also refer to the variables inside anonymous functions with an _
  val odds = seq.filter(_ % 2 != 0)

  // we can chain function calls
  val oddsDoubled = seq.filterNot(_ % 2 == 0).map(_ * 2)

  // sum the list
  val sum = seq.sum

  // another way to sum the list
  val sum1 = seq.fold(0)(_ + _)

  // one more way to sum the list
  val sum2 = seq.reduce(_ + _)

  // find the first matching element
  val optFirst: Option[Int] = seq.find(_ == 5)

  // find the last matching element
  val optLast: Option[Int] = seq.findLast(_ == 5)

  // make a string
  val s = seq.mkString(", ")

  // make a string with a prefix and a suffix
  val sPS = seq.mkString("[<", ", ", ">]")

  // a range (from 0, 1, ... 10)
  val rangeInclusive = 0 to 10

  // a range (from 0, 1, ... 9)
  val rangeExcludingLast = 0 until 10
}
