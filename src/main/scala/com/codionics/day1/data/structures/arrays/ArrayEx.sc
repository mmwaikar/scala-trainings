import scala.collection.mutable.ArrayBuffer

/*
   * Use an Array if the length is fixed, and an ArrayBuffer if the length can vary.
   * Don’t use new when supplying initial values.
   * Use () to access elements.
   * Use for (elem <- arr) to traverse the elements.
   * Use for (elem <- arr if . . . ) . . . yield . . . to transform into a new array.
   * Scala and Java arrays are interoperable; with ArrayBuffer, use scala.collection.JavaConversions.
   */

val nums = new Array[Int](10)

val a = new Array[String](10)

// Note: No new when you supply initial values
val s = Array("Hello", "World")

val hello = s(0)

s(0) = "Goodbye"
s

val b = ArrayBuffer[Int]()

b += 1

b += (2, 3, 4, 5)

b ++= Array(6, 7, 8)

b.trimEnd(2)

b

b.insert(2, 6)

b.insertAll(2, Array(7, 8, 9))

