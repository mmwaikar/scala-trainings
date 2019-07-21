package com.codionics.day2.exceptions

import java.io.{IOException, InputStream}
import java.net.{MalformedURLException, URL}

import scala.math.sqrt

object ExceptionEx {

  // Scala exceptions work the same way as in Java or C++. When you throw an exception, the
  // current computation is aborted, and the runtime system looks for an exception handler
  // that can accept an IllegalArgumentException. Control resumes with the innermost such
  // handler. If no such handler exists, the program terminates. As in Java, the objects that
  // you throw need to belong to a subclass of java.lang.Throwable. However, unlike Java,
  // Scala has no “checked” exceptions—you never have to declare that a function or method
  // might throw an exception.

  // A throw expression has the special type Nothing. That is useful in if/else expressions.
  // If one branch has type Nothing, the type of the if/else expression is the type of the other branch.

  val x = 25

  val squareRoot: Double =
    if (x >= 0) sqrt(x)
    else throw new IllegalArgumentException("x should not be negative")

  // The first branch has type Double, the second has type Nothing. Therefore, the if/else
  // expression also has type Double.

  /* Catching exceptions */

  // The syntax for catching exceptions is modeled after the pattern matching syntax -
  var is: InputStream = _
  val url = new URL("http:/malformed-url.com/")

  try {
    process(url)
    is = url.openStream()

  } catch {

    case _: MalformedURLException => println("Bad URL: " + url)
    case ex: IOException => ex.printStackTrace()

  } finally {
    // lets you dispose of a resource whether or not an exception has occurred
    is.close()
  }

  private def process(url: URL): String = url.toString
}
