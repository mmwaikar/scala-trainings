package com.codionics.day1.sclasses

import scala.beans.BeanProperty
import scala.collection.mutable.ArrayBuffer

object ClassEx {

  // Fields in classes automatically come with getters and setters.
  // You can replace a field with a custom getter/setter without changing the
  // client of a class—that is the “uniform access principle.”
  // Use the @BeanProperty annotation to generate the JavaBeans getXxx/setXxx methods.
  // Every class has a primary constructor that is “interwoven” with the class definition.
  // Its parameters turn into the fields of the class.
  // The primary constructor executes all statements in the body of the class.
  // Auxiliary constructors are optional. They are called this.

  // In Scala, a class is not declared as public. A Scala source file can
  // contain multiple classes, and all of them have public visibility.

  class Counter {
    private var value = 0 // You must initialize the field

    def increment() {
      value += 1
    } // Methods are public by default

    def current() = value

    // In Scala (as well as in Java or C++), a method can access the private
    // fields of all objects of its class.

    // Can access private field of other object
    def isLess(other: Counter) = value < other.value

    /* Object private fields */

    // Scala allows an even more severe access restriction, with the private[this] qualifier:
    private[this] var otherValue = 0 // Accessing someObject.otherValue is not allowed

    // the below is not allowed, the error is - "Symbol otherValue is inaccessible from this place"
    // def isLessOtherValue(other: Counter): Boolean = otherValue < other.otherValue

    // This access is sometimes called object-private.
    // for an object-private field, no getters and setters are generated at all.
  }

  val myCounter = new Counter // Or new Counter()
  myCounter.increment()
  println(myCounter.current)

  // You can call a parameterless method (such as current) with or without parentheses
  myCounter.current // OK
  myCounter.current() // Also OK

  // Which form should you use? It is considered good style to use () for a
  // mutator method (a method that changes the object state), and to drop the
  // () for an accessor method (a method that does not change the object state).
  // You can enforce this style by declaring current without ()

  // Getters and setters are better than public fields because they let you
  // start with simple get/set semantics and evolve them as needed.

  // Scala provides getter and setter methods for every field. Here, we define
  // a public field

  class Person {
    var age = 0
  }

  // Scala generates a class for the JVM with a private age field and getter and
  // setter methods. These methods are public because we did not declare age as
  // private. (For a private field, the getter and setter methods are private.)
  // In Scala, the getter and setter methods are called age and age_=.

  val fred = new Person
  println(fred.age) // Calls the method fred.age()
  fred.age = 21 // Calls fred.age_=(21)

  // At any time, you can redefine the getter and setter methods yourself.
  class PersonSG {
    private var privateAge = 0 // Make private and rename

    def age = privateAge

    def age_=(newValue: Int) {
      if (newValue > privateAge) privateAge = newValue; // Can't get younger
    }
  }

  // The user of your class still accesses fred.age, but now Fred can’t get younger
  val freddy = new PersonSG
  freddy.age = 30
  freddy.age = 21
  println(freddy.age) // 30

  // It may sound scary that Scala generates getter and setter methods for
  // every field. But you have some control over this process.
  // If the field is private, the getter and setter are private.
  // If the field is a val, only a getter is generated.
  // If you don’t want any getter or setter, declare the field as private[this]

  // Sometimes you want a read-only property with a getter but no setter.
  // If the value of the property never changes after the object has been
  // constructed, use a val field
  class PersonV {
    var firstName: String = "Sunil"
    var lastName: String = "Singh"
    val name = s"$firstName $lastName"
  }

  // As you saw in the preceding sections, Scala provides getter and setter
  // methods for the fields that you define. However, the names of these
  // methods are not what Java tools expect. The JavaBeans specification
  // defines a Java property as a pair of getFoo/setFoo methods (or just a
  // getFoo method for a read-only property). Many Java tools rely on this
  // naming convention. When you annotate a Scala field with @BeanProperty,
  // then such methods are automatically generated.

  class PersonBean {
    @BeanProperty var name: String = _
  }

  // the above declaration, generates four methods:
  // 1. name: String
  // 2. name_=(newValue: String): Unit
  // 3. getName(): String
  // 4. setName(newValue: String): Unit

  /* Constructors */

  // A Scala class has one constructor that is more important than all the
  // others, called the primary constructor. In addition, a class may have
  // any number of auxiliary constructors -
  // 1. The auxiliary constructors are called this.
  // 2. Each auxiliary constructor must start with a call to a previously
  // defined auxiliary constructor or the primary constructor.

  class PersonAux {
    private var name = ""

    private var age = 0

    def this(name: String) { // An auxiliary constructor
      this() // Calls primary constructor
      this.name = name
    }

    def this(name: String, age: Int) { // Another auxiliary constructor
      this(name) // Calls previous auxiliary constructor
      this.age = age
    }
  }

  val p1 = new PersonAux // Primary constructor
  val p2 = new PersonAux("Fred") // First auxiliary constructor
  val p3 = new PersonAux("Fred", 42) // Second auxiliary constructor

  /* The primary constructor */

  // The primary constructor is not defined with a this method.
  // Instead, it is interwoven with the class definition.
  // 1. The parameters of the primary constructor are placed immediately after the
  // class name.
  // 2. The primary constructor executes all statements in the class definition.

  class PersonP(val name: String, private var age: Int) {
    // Parameters of primary constructor in (...)

    println("Just constructed another person")
    // the println statement is a part of the primary constructor.
    // It is executed whenever an object is constructed.
  }

  // Parameters of the primary constructor turn into fields that are initialized
  // with the construction parameters. In our example, name and age become
  // fields of the Person class. A constructor call such as
  new PersonP("Fred", 42) //sets the name and age fields.

  // Construction parameters can also be regular method parameters, without
  // val or var. How these parameters are processed depends on their usage
  // inside the class.
  //
  // If a parameter without val or var is used inside at least one method, it
  // becomes a field. Otherwise, the parameter is not saved as a field. It’s
  // just a regular parameter that can be accessed in the code of the primary
  // constructor.

  /*
   * Primary constructor parameter      | Generated field/methods
   *
   * name: String                       | object-private field, or no field if no method uses name
   * private val/var name: String       | private field, private getter/setter
   * val/var name: String               | private field, public getter/setter
   * @BeanProperty val/var name: String | private field, public Scala and JavaBeans getters/setters
   *
   */

  // To make the primary constructor private, place the keyword private like this
  class PersonPP private(val id: Int) { }

  /* Nested classes */
  class PersonWithAddress {

    class Address(city: String, state: String) {
      val contacts = new ArrayBuffer[Address]
    }

    private val addresses = new ArrayBuffer[Address]

    def join(city: String, state: String) = {
      val a = new Address(city, state)
      addresses += a
      a
    }
  }

  val x = new PersonWithAddress
  val y = new PersonWithAddress

  // In Scala, each instance has its own class Address, just like each
  // instance has its own field addresses. That is, x.Address and y.Address
  // are different classes.

  // In a nested class, you can access the this reference of the enclosing
  // class as EnclosingClass.this, like in Java. If you like, you can
  // establish an alias for that reference with the following syntax:

  class PersonRef {
    outer =>

    class Inner {}
  }

  // The class Network { outer => syntax makes the variable outer refer to
  // Network.this. You can choose any name for this variable. The name self
  // is common, but perhaps confusing when used with nested classes.
}
