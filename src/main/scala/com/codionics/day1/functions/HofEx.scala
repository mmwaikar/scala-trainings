package com.codionics.day1.functions

object HofEx {

  def add2(n: Int): Int = n + 2

  def add3(n: Int): Int = n + 3

  /* Functions as values */

  // In Scala, a function is a first class citizen - we can store a function
  // in a variable, just like a number. Here fun is a variable containing a
  // function, not a fixed function.
  val fun = add2 _

  // The _ behind the add2 function indicates that you really meant the function,
  // and you didn’t just forget to supply the arguments.
  val four = fun(2)

  /* Higher Order Functions */

  // now suppose, we don't know at compile time, how much to add to a number?
  // a HOF (which takes a function as an argument)
  def adder(n: Int, adderFn: Int => Int): Int = adderFn(n)
  val seven = adder(5, add2)
  val eight = adder(5, add3)

  /* Parameter Inference */

  // When you pass an anonymous function to another function or method, Scala helps
  // you out by deducing types when possible. So the above two statements were fine
  // because add2 and add3 both confirm to the type signature expected for adderFn
  // (argument). So, if we have to pass any other adderFn, we could write -
  val nine = adder(5, (x: Int) => x + 4)

  // but we don't need to because the compiler is smart enough to infer that the type
  // of x must be Int, so the above could be shortened to -
  val nine1 = adder(5, (x) => x + 4)

  // As a special bonus, for a function that has just one parameter, you can omit the
  // () around the parameter, so we could now use -
  val nine2 = adder(5, x => x + 4)

  // It gets better. If a parameter occurs only once on the right-hand side of the =>,
  // you can replace it with an underscore - This is the ultimate in comfort, and it is
  // also pretty easy to read: a function that adds 4 to something.
  val nine3 = adder(5, 4 + _)

  // let's see one more example of why passing a function to another function is very powerful
  def add(a: Int, b: Int): Int = a + b
  def mul(a: Int, b: Int): Int = a * b

  def addAndPrint(a: Int, b: Int): Unit = {
    val sum = add(a, b)
    println(s"The addition of $a and $b is: $sum")
  }

  def mulAndPrint(a: Int, b: Int): Unit = {
    val mult = mul(a, b)
    println(s"The multiplication of $a and $b is: $mult")
  }

  // another HOF (the result of refactoring addAndPrint & mulAndPrint
  def doSomethingAndPrint(a: Int, b: Int, whatToDo: (Int, Int) => Int, op: String): Unit = {
    val result = whatToDo(a, b)
    println(s"The $op of $a and $b is: $result")
  }

  // now let's see an example of a HOF which returns a function
  def mulBy(factor : Double): Double => Double = (x : Double) => factor * x

  // The power of mulBy is that it can deliver functions that multiply by any amount
  val double = mulBy(2)

  val triple: Double => Double = mulBy(3)
  val twelve = triple(4) // notice, x is 4 and factor is 3

  /* Closures */

  // In Scala, you can define a function inside any scope: in a package, in a class,
  // or even inside another function or method. In the body of a function, you can
  // access any variables from an enclosing scope. That may not sound so remarkable,
  // but note that your function may be called when the variable is no longer in scope.

  // Now let's consider the above two calls -
  //
  // The first call to mulBy sets the parameter variable factor to 2. That variable is
  // referenced in the body of the function (x : Double) => factor * x, which is stored
  // in double. Then the parameter variable factor is popped off the runtime stack.
  //
  // Next, mulBy is called again, now with factor set to 3. That variable is referenced
  // in the body of the function (x : Double) => factor * x, which is stored in triple.
  // Each of the returned functions has its own setting for factor. Such a function is
  // called a closure. A closure consists of code together with the definitions of any
  // non-local variables that the code uses.

  /* Currying */

  // Currying (named after logician Haskell Brooks Curry) is the process of turning a
  // function that takes two arguments into a function that takes one argument. That
  // function returns a function that consumes the second argument. The mul function
  // above can be rewritten as -
  def mulOneAtATime(a: Int): Int => Int = (b: Int) => a * b

  // so to multiply two numbers we can call
  val twenty = mulOneAtATime(5)(4)

  // Strictly speaking, the result of mulOneAtATime(5) is the function (b: Int) => 5 * b.
  // That function is applied to 4, yielding 20.

  // There is a shortcut for defining such curried functions in Scala
  def mulOneAtATimeCurried(a: Int)(b: Int): Int = a * b

  /* Partial application */

  // Now, with this multiple parameter lists, we’ve prepared our function for partial application.
  // So, if we supply only one argument to the mulOneAtATimeCurried (or even mulOneAtATime) function
  // then we'se used partial application to get a function (which here, takes one argument)
  val quadruple: Int => Int = mulOneAtATimeCurried(4)
}
