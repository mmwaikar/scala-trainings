package com.codionics.day1.traits

trait TraitEx {

  // a trait can define a field as a def
  def id: String
}

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
