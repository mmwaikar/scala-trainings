package com.codionics.day2.traits

import java.io.{IOException, PrintWriter}
import java.util.Date

import javax.swing.JFrame

// A class can implement any number of traits.
// Traits can require that implementing classes have certain fields, methods, or superclasses.
// Unlike Java interfaces (before Java 8), a Scala trait can provide implementations of methods and fields.
// When you layer multiple traits, the order matters—the trait whose methods execute first goes to the back.
// All Java interfaces can be used as Scala traits.
// Traits cannot have constructor parameters. Every trait has a single parameterless constructor.

trait TraitEx {

  // a trait can define a field as a def - this is an abstract field
  // because no value has been provided (if an initial value is provided
  // then it becomes a concrete field)
  def id: String
}

// a class extends a trait, not implements
// Any uninitialized field(s) in a trait must be overridden in a concrete subclass.
class Traitor extends TraitEx {

  // which can be extended by a class as a "val"
  val id: String = "hey"
}

class NoTraitor extends TraitEx {

  // or, it can be extended by a class as a "var"
  var id: String = "hee"
}

trait Logger {

  // an abstract method - because it doesn't have an implementation
  def logIt(message: String)

  // but methods in traits can also have default implementations (like in Java 8 interfaces)
  def log(message: String) = {
    println(s"Message is: $message")
  }
}

trait EmailSender {

  def send(message: String, emailId: String): Unit = {
    println(s"send $message to email id: $emailId")
  }
}

// a class can use the functionality from multiple traits
// the other traits are added using the with keyword
// In Scala (and other programming languages that allow this), we say that the
// EmailSender functionality is "mixed in" with the OfficeWorker class.

class OfficeWorker extends Logger with EmailSender {

  override def logIt(message: String): Unit = {
    println("Provide some implementation")
  }

  def writeMemo() = {
    val emailId = "some@company.com"
    val msg = "Hello, why were you absent yesterday?"

    log(msg)
    send(msg, emailId)
  }
}

trait Logged {
  def log(msg: String) {}
}

trait ConsoleLogger extends Logged {
  override def log(msg: String) { println(msg) }
}

trait FileLogger extends Logged {

  override def log(msg: String) {
    println(s"write to file: $msg")
  }
}

trait Account {

  def balance: Double
}

class SavingsAccount extends Account with Logged {

  var balance: Double = 50

  def deposit(amount: Double): Double = {
    if (amount > 0) {
      balance += amount
      balance
    } else
      balance
  }

  def withdraw(amount: Double): Double = {
    if (amount > balance) {
      log("Insufficient funds")
      balance
    } else {
      balance -= amount
      balance
    }
  }
}

/* Layered traits */

// You can add, to a class or an object, multiple traits that invoke each other starting with the last one.

trait TimestampLogger extends Logged {

  override def log(msg: String) {
    super.log(new java.util.Date() + " " + msg)
  }
}

trait ShortLogger extends Logged {
  val maxLength = 15

  override def log(msg: String) {
    super.log(
      if (msg.length <= maxLength) msg
      else msg.substring(0, maxLength - 3) + "..."
    )
  }
}

// now suppose the maxLength field above was abstract, then there is a way
// to supply the value while you construct objects on the fly (see the
// comment below /* === */

trait AbstractShortLogger extends Logged {
  val maxLength: Int
}

// Note that each of the log methods passes a modified message to super.log.
// With traits, super.log does not have the same meaning as it does with classes.
// (If it did, then these traits would be useless—they extend Logged whose log
// method does nothing.) Instead, super.log calls the next trait in the trait
// hierarchy, which depends on the order in which the traits are added. Generally,
// traits are processed starting with the last one.

/* Traits for rich interfaces */

trait RichLogger {

  def log(msg: String)

  def info(msg: String) { log("INFO: " + msg) }

  def warn(msg: String) { log("WARN: " + msg) }

  def severe(msg: String) { log("SEVERE: " + msg) }
}

class RichSavingsAccount extends Account with RichLogger {

  var balance: Double = 0

  def withdraw(amount: Double) {
    if (amount > balance)
      severe("Insufficient funds")
    else
      info(s"allowed to withdraw $amount")
  }

  override def log(msg:String) { println(msg) }
}

/* Trait construction order */

// Just like classes, traits can have constructors, made up of field
// initializations and other statements in the trait’s body.

trait PrintFileLogger extends Logger {
  val out = new PrintWriter("app.log") // Part of the trait's constructor

  out.println("# " + new Date().toString) // Also part of the constructor

  override def log(msg: String) {
    out.println(msg)
    out.flush()
  }
}

// Constructors execute in the following order:
// The superclass constructor is called first.
// Trait constructors are executed after the superclass constructor but before the class constructor.
// Traits are constructed left-to-right.
// Within each trait, the parents get constructed first.
// If multiple traits share a common parent, and that parent has already been constructed, it is not constructed again.
// After all traits are constructed, the subclass is constructed.

class SavingsAccount1 extends Account with FileLogger with ShortLogger {
  var balance = 0
}

// The constructors execute in the following order:
// 1. Account (the superclass).
// 2. Logger (the parent of the first trait).
// 3. FileLogger (the first trait).
// 4. ShortLogger (the second trait). Note that its Logger parent has already been constructed.
// 5. SavingsAccount1 (the class).

// The constructor ordering is the reverse of the linearization of the class.
// The linearization is a technical specification of all supertypes of a type.
// It is defined by the rule: If C extends C1 with C2 with . . . with Cn, then
// lin(C) = C » lin(Cn) » . . . » lin(C2) » lin(C1)
// Here, » means “concatenate and remove duplicates, with the right winning out.”
//
// lin(SavingsAccount1)
// = SavingsAccount1 » lin(ShortLogger) » lin(FileLogger) » lin(Account)
// = SavingsAccount1 » (ShortLogger » Logger) » (FileLogger » Logger) » lin(Account)
// = SavingsAccount1 » ShortLogger » FileLogger » Logger » Account.
//
// The linearization gives the order in which super is resolved in a trait.
// For example, calling super in a ShortLogger invokes the FileLogger method,
// and calling super in a FileLogger invokes the Logger method.

/* Traits extending classes */

// As you have seen, a trait can extend another trait, and it is common to have a
// hierarchy of traits. Less commonly, a trait can also extend a class. That
// class becomes a superclass of any class mixing in the trait.

trait LoggedException extends Exception with Logged {

  def log() {
    log(getMessage()) // getMessage method is inherited from the Exception superclass
  }
}

// Now let’s form a class that mixes in this trait
class UnhappyException extends LoggedException { // This class extends a trait
  override def getMessage() = "arggh!"
}

// What if our class already extends another class? That’s OK, as long as it’s a
// subclass of the trait’s superclass.
class AnotherException extends IOException with LoggedException

// However, if our class extends an unrelated class, then it is not possible to mix in the trait.
// For example, you cannot form the following class:
//class UnhappyFrame extends JFrame with LoggedException

// we get the error - illegal inheritance; superclass JFrame is not a subclass
// of the superclass Exception of the mixin trait LoggedException

/* Self types */

// When a trait extends a class, there is a guarantee that the superclass
// is present in any class mixing in the trait. Scala has an alternate
// mechanism for guaranteeing this: self types.
// When a trait starts out with this: Type => then it can only be mixed into
// a subclass of the given type.

trait SelfTypeLoggedException extends Logged {
  this: Exception =>

  def log() {
    log(getMessage())
  }
}
// Note that the trait does not extend the Exception class. Instead, it has a
// self type of Exception. That means, it can only be mixed into subclasses of
// Exception. In the trait’s methods, we can call any methods of the self type.
// For example, the call to getMessage() in the log method is valid, since we
// know that this must be an Exception.

/* Structural types */
// Self types can also handle structural types — types that merely specify the
// methods that a class must have, without naming the class. Here is the
// LoggedException using a structural type:

trait StructuralTypeLoggedException extends Logged {
  this: { def getMessage() : String } =>

  def log() {
    log(getMessage())
  }
}
// The trait can be mixed into any class that has a getMessage method.

// Scala needs to translate traits into classes and interfaces of the JVM.

object TraitExamples {

  /* Objects with traits */

  // You can add a trait to an individual object when you construct it.
  val acct1 = new SavingsAccount with ConsoleLogger
  val acct2 = new SavingsAccount with FileLogger

  val acct3 = new SavingsAccount with ConsoleLogger with TimestampLogger with ShortLogger
  acct3.withdraw(100)
  // As you can see, the ShortLogger’s log method was called first, and its
  // call to super.log called the TimestampLogger.

  val acct4 = new SavingsAccount with ConsoleLogger with ShortLogger with TimestampLogger
  acct4.withdraw(100)
  // Here, the TimestampLogger appeared last in the list of traits. Its log
  // message was called first, and the result was subsequently shortened.

  /* === */
  val acct = new SavingsAccount with ConsoleLogger with AbstractShortLogger {
    val maxLength = 20
  }
}
