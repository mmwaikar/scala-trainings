package com.codionics.day1.basics

import scala.math._

object ControlStructuresEx {

  // An if expression has a value.
  // A block has a value—the value of its last expression.
  // The Scala for loop is like an “enhanced” Java for loop.
  // Semicolons are (mostly) optional.
  // The void type is Unit.
  // Avoid using return in a function.
  // Beware of missing = in a function definition.
  // Exceptions work just like in Java or C++, but you use a “pattern matching” syntax for catch.
  // Scala has no checked exceptions.

  /* Conditional expressions */
  // In Scala, an if/else has a value, namely the value of the expression that follows the if or else.
  val x = 0
  val y = 1

  val zero = if (x < y) x else y
  val one = if (x > y) x else y

  // remember, Scala does not have the (? :) ternary operator like Java or C

  // In Scala, every expression has a type. So the types of zero and one above are Int(s).
  // The type of a mixed-type expression, such as the one below, is the common supertype of both branches.
  val commonSuperType: Any = if (x > 0) "positive" else -1

  // what is the value of unit below?
  val unit = if (x > y) 1

  // If the else part is omitted (as above) then it is possible that the if statement yields no value.
  // However, in Scala, every expression is supposed to have some value. This is finessed by introducing
  // a class Unit that has one value, written as (). The if statement without an else is equivalent to
  // if (x > y) 1 else ()
  // Think of () as a placeholder for “no useful value,” and think of Unit as the analog of void in Java or C++.
  // Technically speaking, void has no value whereas Unit has one value that signifies “no value”.

  /* Statement termination */
  // In Java and C++, every statement ends with a semicolon. In Scala—like in JavaScript and
  // other scripting languages—a semicolon is never required if it falls just before the end of the line.
  // However, if you want to have more than one statement on a single line, you need to separate
  // them with semicolons. For example,
  var n, r = 5
  val ex = if (n > 0) {
    r = r * n; n -= 1
  } // Because of the }, no semicolon is needed after the second statement.

  /* Block Expressions and Assignments */
  // In Scala, a { } block contains a sequence of expressions, and the result is also an expression.
  // The value of the block is the value of the last expression.
  val dx, dy = 2
  val x0, y0 = 1

  val distance = { val dx = x - x0; val dy = y - y0; sqrt(dx * dx + dy * dy) }

  /* Loops */
  while (n > 0) {
    r = r * n
    n -= 1
  }

  // or the for loop
  for (i <- 1 to n)
    r = r * i

  /* Advanced for loops and for comprehensions */
  // You can have multiple generators of the form variable <- expression. Separate them by semicolons. For example,
  for (i <- 1 to 3; j <- 1 to 3) print((10 * i + j) + " ") // prints 11 12 13 21 22 23 31 32 33

  // Each generator can have a guard, a Boolean condition preceded by if:
  for (i <- 1 to 3; j <- 1 to 3 if i != j)
    print((10 * i + j) + " ") // prints 12 13 21 23 31 32

  // When the body of the for loop starts with yield, then the loop constructs a collection of values,
  // one for each iteration - This type of loop is called a for comprehension.
  for (i <- 1 to 10) yield i % 3 // yields Vector(1, 2, 0, 1, 2, 0, 1, 2, 0, 1)
}
