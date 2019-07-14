package com.codionics.day2.sclasses

// The extends and final keywords are as in Java.
// You must use override when you override a method.
// Only the primary constructor can call the primary superclass constructor.
// You can override fields.

// You extend a class in Scala just like you would in Java—with the extends keyword.
// As in Java, you specify fields and methods that are new to the subclass or that
// override methods in the superclass.
//
// As in Java, you can declare a class final so that it cannot be extended. You can
// also declare individual methods or fields final so that they cannot be overridden.

// In Scala, you must use the override modifier when you override a method that isn’t
// abstract. The override modifier can give useful error messages in a number of common situations, such as
//  - When you misspell the name of the method that you are overriding
//  - When you accidentally provide a wrong parameter type in the overriding method
//  - When you introduce a new method in a superclass that clashes with a subclass method

class Person(name: String) {

  // As in Java or C++, you can declare a field or method as protected. Such a member
  // is accessible from any subclass, but not from other locations.
  protected def printName(): Unit = println(s"name: $name")

  override def toString: String = s"Person($name)"
}

// Recall that a class has one primary constructor and any number of auxiliary constructors,
// and that all auxiliary constructors must start with a call to a preceding auxiliary
// constructor or the primary constructor. As a consequence, an auxiliary constructor can
// never invoke a superclass constructor directly. The auxiliary constructors of the subclass
// eventually call the primary constructor of the subclass. Only the primary constructor can
// call a superclass constructor. Recall that the primary constructor is intertwined with the
// class definition. The call to the superclass constructor is similarly intertwined.

class Employee(name: String, department: String) extends Person(name) {

  // invoke super class methods with the keyword super
  override def toString: String =
    s"${super.toString} and also Employee($name, $department)"
}

/* Abstract classes and fields */

abstract class AbstractPerson(val name: String) {

  // unlike Java, you do not use the abstract keyword for an abstract
  // method. You simply omit its body.
  def id: Int

  val idVal: Int // No initializer—this is an abstract field with an abstract getter method

  var nameVar: String // Another abstract field, with abstract getter and setter methods

  // This class defines abstract getter methods for the id and name fields,
  // and an abstract setter for the name field. The generated Java class has
  // no fields.
}

class ConcreteEmployee(val idVal: Int, name: String)
    extends AbstractPerson(name) {
  // Subclass has concrete idVal property

  def id: Int = name.hashCode // override keyword not required

  var nameVar: String = "" // and concrete name property
}

// The Scala type hierarchy - https://docs.scala-lang.org/tour/unified-types.html
// The classes that correspond to the primitive types in Java, as well as the type
// Unit, extend AnyVal. All other classes are subclasses of the AnyRef class,
// which is a synonym for the Object class from the Java or .NET virtual machine.
// Both AnyVal and AnyRef extend the Any class, the root of the hierarchy. The Any
// class defines methods isInstanceOf, asInstanceOf, and the methods for equality and hash codes

// At the other end of the hierarchy are the Nothing and Null types. Null is the
// type whose sole instance is the value null. The Nothing type has no instances.
// It is occasionally useful for generic constructs. For example, the empty list
// Nil has type List[Nothing], which is a subtype of List[T] for any T.

/* Object equality */

// In Scala, the eq method of the AnyRef class checks whether two references refer to
// the same object. The equals method in AnyRef calls eq. When you implement a class,
// you should consider overriding the equals method to provide a natural notion of
// equality for your situation.

class Item(val description: String, val price: Double) {

  // note the parameter type of Any
  final override def equals(other: Any): Boolean = {
    val that = other.asInstanceOf[Item]

    if (that == null)
      false
    else
      description == that.description && price == that.price
  }

  // When you define equals, remember to define hashCode as well. The hash code
  // should be computed only from the fields that you use in the equality check.

  final override def hashCode: Int =
    13 * description.hashCode + 17 * price.hashCode
}

object InheritanceEx {

  val nullP: Person = null
  val p = new Person("Kartik")
  val e = new Employee("Sharad", "HR")

  // To test whether an object belongs to a given class, use the isInstanceOf method.
  // The e.isInstanceOf[Employee] test succeeds if e refers to an object of class
  // Employee or its subclass (such as Manager).

  val isPPerson = p.isInstanceOf[Person] // true
  val isPEmployee = p.isInstanceOf[Employee] // false
  val isEPerson = e.isInstanceOf[Person] // true
  val isEEmployee = e.isInstanceOf[Employee] // true
  val isNullPerson = null.isInstanceOf[Person] // false

  // similarly, there is an asInstanceOf method
  val p1 = p.asInstanceOf[Person]
  val p2 = p.asInstanceOf[Employee] // java.lang.ClassCastException: Person cannot be cast to Employee
  val e1 = e.asInstanceOf[Person]
  val e2 = e.asInstanceOf[Employee]
  val null2 = null.asInstanceOf[Person] // null

  // If you want to test whether e refers to an Employee object, but not a subclass,
  // use the classOf method. The classOf method is defined in the scala.Predef object
  // that is always imported.
  val isEEmployeeNotPerson = e.getClass == classOf[Employee]
  val isEPersonNotEmployee = e.getClass == classOf[Person]

  // however, pattern matching is preferable over type checks and cast
  def doSomethingBaseToDerived(p: Person): Unit = {
    p match {
      case p: Person => println(s"$p is only person")
      // following does not make any sense, gives compile time warning, unreachable code
      case e: Employee => println(s"$e is employee")
      case _           => println("neither employee nor person")
    }
  }

  // try to match the most specific (derived) to least specific (base) classes
  def doSomethingDerivedToBase(p: Person): Unit = {
    p match {
      case e: Employee => println(s"$e is employee")
      case p: Person   => println(s"$p is only person")
    }
  }

  doSomethingBaseToDerived(null)

  doSomethingBaseToDerived(p)
  doSomethingBaseToDerived(e)

  // notice the difference between the output of the above 2 vs. below 2 calls
  doSomethingDerivedToBase(p)
  doSomethingDerivedToBase(e)

  // A Scala class can extend a Java class. Its primary constructor must invoke
  // one of the constructors of the Java superclass.
  class Square(x: Int, y: Int, width: Int)
      extends java.awt.Rectangle(x, y, width, width)

  val i1 = new Item("Shirt", 1999.99)
  val i2 = new Item("Shirt", 1999.99)
  val i3 = new Item("Pant", 2599.99)

  println(i1 == i2)
  println(i2 == i3)
}
