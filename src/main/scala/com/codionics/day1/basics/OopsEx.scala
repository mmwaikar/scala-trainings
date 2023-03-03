package com.codionics.day1.basics

/**
  * Why do we need classes?
  * So that we can model real world things / data.
  * 
  * Any real world thing / object -
  * has some properties (state / data) - e.g. a car has 4 wheels
  * displays some behavior (methods)   - e.g. you can accelerate a car, or you can steer it in any direction
  * 
  * There are 4 main OOPS concepts:
  * 
  * Abstraction - the process of abstraction can also be referred to as modelling, where we try to use simple things to 
  * explain complexity. It allows us to hide the internal complexity of things we are trying to model.
  * 
  * Encapsulation (to place in or as if in a capsule) - refers to the bundling of data and methods that operate on that
  * data (together, in a single entity). It may also refer to the limiting of direct access to some of that data (using
  * private, protected and public access keywords).
  * 
  * Inheritence - is a special concept of OOPS languages which lets us derive new classes based on existing classes, so
  * that we may avoid rework.
  * 
  * Polymorphism - allows programmers to use the same word to mean different things in different contexts. This is
  * achieved through method overriding. Overriding a method in a derived class means altering the behavior of the method
  * which the base class has provided.
  * 
  * Method overloading achieves different behavior based on the data passed to a method as parameters.
  * 
  */

// Abstraction / Encapsulation example
class Player(private val firstName: String, private val lastName: String) {
  val name = s"$firstName $lastName"
}

// Inheritence / Polymorphism example
abstract class Greeter {
  def greet(name: String): String
}

class HindiGreeter extends Greeter {
  override def greet(name: String): String = s"Namaste, $name!"
}

class EnglishGreeter extends Greeter {
  override def greet(name: String): String = s"Hello, $name!"
}

object OopsEx extends App {
  val p = new Player("Roger", "Federer")
  println(s"Name of the player: ${p.name}")

  // but you can't access the firstName or the lastName properties, because we have made them private
  // val fn = p.firstName // compilation error
  // val ln = p.lastName  // compilation error

  val name = "Scala"
  val hindiGreeter = new HindiGreeter
  val englishGreeter = new EnglishGreeter

  println(s"Greetings in Hindi: ${hindiGreeter.greet(name)}")
  println(s"Greetings in English: ${englishGreeter.greet(name)}")
}