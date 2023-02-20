package com.codionics.day1.functions

object CallByNameEx extends App {

  def time(): Long = {
    println("In time()")
    System.nanoTime
  }

  def exec(t: Long): Long = {
    println("Entered exec, calling t ...")
    println("t = " + t)
    println("Calling t again ...")
    t
  }

  println(exec(time()))

  // The output:
  // In time()
  // Entered exec, calling t ...
  // t = 1363909521286596000
  // Calling t again ...
  // 1363909521286596000

  // As shown, the values for t are the same. This is because the value of t is
  // determined as soon as this line is invoked: println(exec(time()))
  // That is, t is assigned to the value of time() when the compiler creates the exec method and its t parameter.

  // `t` is now defined as a by-name parameter
  def execByName(t: => Long): Long = {
    println("Entered exec, calling t ...")
    println("t = " + t)
    println("Calling t again ...")
    t
  }

  println(execByName(time()))

  // This time the output is different:
  // Entered exec, calling t ...
  // Entered time() ...
  // t = 1363909593759120000
  // Calling t again ...
  // Entered time() ...
  // 1363909593759480000
  //
  // The two t invocations yield different results. As stated in the Scala Language Specification, this is because:
  // This indicates that the argument is not evaluated at the point of function application, but instead
  // is evaluated at each use within the function. That is, the argument is evaluated using call-by-name.
}
