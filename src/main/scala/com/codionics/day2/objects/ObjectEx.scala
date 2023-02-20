package com.codionics.day2.objects

object ObjectEx {

  // an object is by default singleton! NO NEED for a DESIGN PATTERN

  // Use it when you need a class with a single instance, or when you want
  // to find a home for miscellaneous values or functions.
  //
  // Use objects for singletons and utility methods.
  // A class can have a companion object with the same name.
  // Objects can extend classes or traits.
  // The apply method of an object is usually used for constructing new instances of the companion class.
  // To avoid the main method, use an object that extends the App trait.
  // You can implement enumerations by extending the Enumeration object.

  // Scala has no static methods or fields. Instead, you use the object construct.
  // An object defines a single instance of a class with the features that you want.

  object UniqueIdGenerator {

    private var lastId = 0

    def getNewId(): Int = {
      lastId += 1
      lastId
    }
  }

  // When you need a new unique id in your application, call
  val latestId = UniqueIdGenerator.getNewId()

  // The constructor of an object is executed when the object is first used.
  // In our example, the UniqueIdGenerator constructor is executed with the first call
  // to UniqueIdGenerator.getNewId(). If an object is never used, its constructor is not executed.

  /* Objects Extending a Class or Trait */

  // An object can extend a class and/or one or more traits.
  // The result is an object of a class that extends the given class and/or
  // traits, and in addition has all of the features specified in the object
  // definition. One useful application is to specify default objects that can be shared.

  abstract class UndoableAction(val description: String) {
    def undo(): Unit
    def redo(): Unit
  }

  // A useful default is the “do nothing” action. Of course, we only need one of them.
  object DoNothingAction extends UndoableAction("Do nothing") {
    override def undo() {}
    override def redo() {}
  }

  // The DoNothingAction object can be shared across all places that need this default.
  val actions = Map("open" -> DoNothingAction, "save" -> DoNothingAction)
  // Open and save not yet implemented

  /* The apply method */

  // It is common to have objects with an apply method. The apply method is
  // called for expressions of the form Object(arg1, ..., argN) Typically,
  // such an apply method returns an object of the companion class.

  class Account private (val id: Int, initialBalance: Double) {
    private var balance = initialBalance
  }

  object Account { // The companion object

    private var lastNumber = 0

    private def newUniqueNumber(): Int = { lastNumber += 1; lastNumber }

    def apply(initialBalance: Double) =
      new Account(newUniqueNumber(), initialBalance)
  }

  // Notice, the ctor is private so we can't say val a = new Account()
  // But you can construct an account as
  val acct = Account(1000.0)

  /* Application Objects */

  // Each Scala program must start with an object’s main method of type Array[String] => Unit:
  object Hello {

    def main(args: Array[String]) {
      println("Hello, World!")
    }
  }

  // Instead of providing a main method for your application, you can extend the
  // App trait and place the program code into the constructor body

  object HelloApp extends App {
    println("Hello, World!")
  }

  // If you need the command-line arguments, you can get them from the args property
  object HelloAppWithArgs extends App {

    if (args.length > 0)
      println("Hello, " + args(0))
    else
      println("Hello, World!")
  }

  /* Enumerations */

  // Unlike Java or C++, Scala does not have enumerated types. However, the
  // standard library provides an Enumeration helper class that you can use
  // to produce enumerations. Define an object that extends the Enumeration
  // class and initialize each value in your enumeration with a call to the
  // Value method.

  object TrafficLightColor extends Enumeration {
    val Red, Yellow, Green = Value

    // Alternatively, you can pass IDs, names, or both to the Value method
    val FluorescentRed = Value(0, "Stop")
    val FluorescentYellow = Value(10) // Name "FluorescentYellow"
    val FluorescentGreen = Value("Go") // ID 11

    // If not specified, the ID is one more than the previously assigned one,
    // starting with zero. The default name is the field name.
  }

  // You can now refer to the enumeration values as TrafficLightColor.Red,
  // TrafficLightColor.Yellow, and so on. If that gets too tedious, use a statement

  import TrafficLightColor._
  val red: TrafficLightColor.Value = Red // notice the type of the enumeration

  // The ID of an enumeration value is returned by the id method, and its name
  // by the toString method. The call TrafficLightColor.values yields a set of all values
  for (c <- TrafficLightColor.values)
    println(s"${c.id}: $c")

  // Finally, you can look up an enumeration value by its ID or name. Both of the
  // following yield the object TrafficLightColor.Red
  TrafficLightColor(0) // Calls Enumeration.apply
  TrafficLightColor.withName("Red")
}
