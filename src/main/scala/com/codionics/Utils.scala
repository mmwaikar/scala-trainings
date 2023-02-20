package com.codionics

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

  /* Implicit classes */

  /* Pimp my library pattern */

  // As we saw earlier, implicit function can convert some type A into type B.
  // There is actually no constraints on the type B, it doesn’t have to be a primitive type.
  // Let’s say we have a simple class working on strings.

  case class StringOps(str: String) {
    def yell: String = str.toUpperCase() + "!"

    def isQuestion: Boolean = str.endsWith("?")
  }

  // Now, we can write an implicit function that converts String into our StringOps.
  implicit def stringToStringOps(str: String): StringOps = StringOps(str)

  // Finally, we can use -
  "Hello world".yell // evaluates to "HELLO WORLD!"
  "How are you?".isQuestion // evaluates to 'true'

  // Scala 2.10 introduced implicit classes that can help us reduce the boilerplate
  // of writing implicit functions for conversion. So now we could write -

//  implicit class StringOps(str: String) {
//    def yell: String = str.toUpperCase() + "!"
//
//    def isQuestion: Boolean = str.endsWith("?")
//  }

  // https://docs.scala-lang.org/overviews/core/implicit-classes.html
  //
  // An implicit class is a class marked with the implicit keyword. This keyword makes the
  // class’s primary constructor available for implicit conversions when the class is in scope.
  // To create an implicit class, simply place the implicit keyword in front of an appropriate class.

  /* Implicit class restrictions */

  // 1. They must be defined inside of another trait/class/object.
  //
  // 2. They may only take one non-implicit argument in their constructor.
  //    implicit class Indexer[T](collection: Seq[T], index: Int) // BAD!
  //    implicit class Indexer[T](collection: Seq[T])(implicit index: Index) // OK!
  //
  // 3. There may not be any method, member or object in scope with the same name as the implicit class.
  //    Note: This means an implicit class cannot be a case class.

  implicit class LocalDateOps(d: LocalDate) {

    def toFormat(pattern: String): String = {
      val format = DateTimeFormatter.ofPattern(pattern)
      d.format(format)
    }
  }

  /* Type classes */

  // With implicit objects it is possible to implement type classes — a type system
  // construct that supports ad hoc polymorphism.
}
