package com.codionics.day1.functions

object HofEx {

  def add2(n: Int): Int = n + 2

  def add3(n: Int): Int = n + 3

  // a HOF
  def adder(n: Int, adderFn: Int => Int): Int = n + adderFn(1)

  def add(a: Int, b: Int): Int = a + b

  def addAndPrint(a: Int, b: Int): Unit = {
    val sum = add(a, b)
    println(s"The addition of $a and $b is: $sum")
  }

  def mul(a: Int, b: Int): Int = a * b

  def mulAndPrint(a: Int, b: Int): Unit = {
    val mult = mul(a, b)
    println(s"The multiplication of $a and $b is: $mult")
  }

  // another HOF (the result of refactoring addAndPrint & mulAndPrint
  def doSomethingAndPrint(a: Int, b: Int, whatToDo: (Int, Int) => Int, op: String): Unit = {
    val result = whatToDo(a, b)
    println(s"The $op of $a and $b is: $result")
  }
}
