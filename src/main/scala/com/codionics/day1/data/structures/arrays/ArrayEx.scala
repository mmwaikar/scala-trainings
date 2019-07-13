package com.codionics.day1.data.structures.arrays

import scala.collection.mutable.ArrayBuffer

object ArrayEx {

  /*
   * Inside the JVM, a Scala Array is implemented as a Java array.
   *
   * Use an Array if the length is fixed, and an ArrayBuffer if the length can vary.
   * Don’t use new when supplying initial values.
   * Use () to access elements.
   * Use for (elem <- arr) to traverse the elements.
   * Use for (elem <- arr if . . . ) . . . yield . . . to transform into a new array.
   * Scala and Java arrays are interoperable; with ArrayBuffer, use scala.collection.JavaConversions.
   */

  val nums = new Array[Int](10)

  val a = new Array[String](10)

  val s = Array("Hello", "World")

  val hello = s(0)

  s(0) = "Goodbye"
  println(s)

  val b: ArrayBuffer[Int] = ArrayBuffer[Int]()

  b += 1

  b += (2, 3, 4, 5)

  b ++= Array(6, 7, 8)

  b.trimEnd(2)

  println(b)

  b.insert(2, 6)

  b.insertAll(2, Array(7, 8, 9))

  val x: Array[Int] = b.toArray

  for (i <- 0 until x.length)
    println(s"$i: ${x(i)}")

  // If you don’t need the array index in the loop body, visit the array elements directly
  for (elem <- a)
    println(elem)

  // for comprehensions don't modify the original array, but yield a new one
  val doubledNums: Array[Int] = for (elem <- nums)
    yield elem * 2

  val doubledBs: ArrayBuffer[Int] = for (elem <- b)
    yield elem * 2

  // for comprehension with a guard
  val evensDoubled = for (elem <- b if elem % 2 == 0)
    yield 2 * elem

  // common algorithms
  val unsorted = Array(4, 2, 6, 7, 5, 9, 1)

  val maxEl = unsorted.max
  val sorted = unsorted.sorted
  val sortedWith = unsorted.sortWith(_ > _)
}
