package com.codionics.day2.sets

import scala.collection.immutable.SortedSet
import scala.collection.mutable

object SetEx {

  // A set is a collection of distinct elements.
  val indianPlayers = Set("Rahul", "Rohit", "Bumrah")

  // Trying to add an existing element has no effect.
  val sameSet = indianPlayers + "Rohit"

  // Unlike lists, sets do not retain the order in which elements are inserted.
  // A linked hash set remembers the order in which elements were inserted.
  // It keeps a linked list for this purpose.
  val weekdays = mutable.LinkedHashSet("Mon", "Tue", "Wed", "Thu", "Fri")

  // If you want to iterate over elements in sorted order, use a sorted set:
  val sortedSet = SortedSet(1, 2, 3, 4, 5, 6)

  // usual set functions
  val digits = Set(1, 7, 2, 9)
  val primes = Set(2, 3, 5, 7)

  val unioned = digits.union(primes)
  val commonInBoth = digits.intersect(primes)
  val inPrimesNotInDigits = primes.diff(digits)

  // to remove an element, use the - operator
  val oddPrimes = primes - 2
}
