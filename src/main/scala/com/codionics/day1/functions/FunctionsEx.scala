package com.codionics.day1.functions

trait FunctionsEx {

  // You must specify the types of all parameters. However, as long as the
  // function is not recursive, you need not specify the return type.

  // single line function (no need for a return keyword)
  def multiplyByTwo(n: Int) = n * 2

  // can also be written as
  def addTwo(n: Int) =
    n + 2

  // if the return type is not specified, the compiler infers it, but we can specify it
  def addThree(n: Int): Int = n + 3

  // for functions which do not return anything (void in Java), the return type is Unit
  def sayHello(name: String): Unit = println(s"Hello, $name!")

  // the above is an example of string interpolation (even ES6 has this feature called template strings)

  // If the body of the function requires more than one expression, use a block.
  // The last expression of the block becomes the value that the function returns.

  // with a recursive function, you must specify the return type
  def fac(n: Int): Int = if (n <= 0) 1 else n * fac(n - 1)

  /* Default and named arguments */

  // functions can have default arguments
  def greet(name: String, gender: String = "M") = {
    // multi-line functions should be wrapped in a block (i.e. curly braces {})

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
    *
    * If you supply fewer arguments than there are parameters, the defaults are applied from the end.
    */

  // You can also specify the parameter names when you supply the arguments. For ex,
  val g = greet(gender = "F", name = "Johnny")

  // Note that the named arguments need not be in the same order as the parameters (as shown above).
  // You can mix unnamed and named arguments, provided the unnamed ones come first:
  greet("Jimmy", gender = "X")

  /* Variable arguments */

  def sum(args: Int*) = {
    var result = 0

    for (arg <- args) result += arg
      result
  }

  // we can call it with any number of arguments
  val six = sum(1, 2, 3)
  val ten = sum(1, 2, 3, 4)

  // so what if we already have a sequence of values?
  val seq = Seq(1, 2, 3, 4, 5)

  // we can't directly pass a sequence to the above function, so below is not allowed
  // val fifteen = sum(seq)

  // The remedy is to tell the compiler that you want the parameter to be
  // considered an argument sequence. Append : _*, like this:
  val fifteen = sum(seq: _*)

  // on the RHS is an anonymous function, which has been stored in a variable called timesThree
  val timesThree: Double => Double = (x: Double) => x * 3

  // we don't have to store it in a variable, we can directly pass it to a function
  val tripled = seq.map((x: Int) => x * 3)

  // another short way to write an anonymous function, notice, the type is inferred from the type of the seq
  val doubled = seq.map(x => x * 2)

  def getMinMax(a: Int, b: Int): Unit = {

    // nested functions
    def getMin = if (a < b) a else b
    def getMax = if (a > b) a else b

    println(s"Min of $a, $b is: $getMin")
    println(s"Max of $a, $b is: $getMax")
  }
}
