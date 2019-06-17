package com.codionics.functions

trait FunctionsEx {

  // single line function
  def multiplyByTwo(n: Int) = n * 2

  // can also be written as
  def addTwo(n: Int) =
    n + 2

  // if the return type is not specified, the compiler infers it, but we can specify it
  def addThree(n: Int): Int = n + 3

  // for functions which do not return anything (void in Java), the return type is Unit
  def sayHello(name: String): Unit = println(s"Hello, $name!")

  // the above is an example of string interpolation (even ES6 has this feature called template strings)

  // functions can have default arguments
  def greet(name: String, gender: String = "M") = {
    // multi-line functions should be wrapperd in curly braces {}

    /**
      * NOTE: one big difference is, that the below if condition returns a value, so it
      * is an expression, whereas in Java, it is a statement
      */
    if (gender == "M")
      s"Hello, Mr. $name"
    else
      s"Hello, Ms. $name"
  }

  /**
  * The above is an example of an overloaded function - it can be called as -
  *
  * greet("Manoj"), or
  * greet("Piya", "F")
  *
  * So, instead of having 3-4 different overloaded functions, have a single function
  * with multiple default arguments.
  */
}
