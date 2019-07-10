package com.codionics.day2

import scala.util.{Failure, Success, Try}

object EitherOptionTryEx {

  case class Employee(id: Int, name: String)
  val employees = getEmployees

  val foundId = 3
  val notFoundId = 5

  val someEmployee = getById(foundId)
  val noneEmployee = getById(notFoundId)

  printEmployee(someEmployee)
  printEmployee(noneEmployee)

  val rightEmployee = getEitherById(foundId)
  val leftEmployee = getEitherById(notFoundId)

  printEmployee(rightEmployee)
  printEmployee(leftEmployee)

  val noExceptionEmployee = getExceptionalById(foundId)
  val exceptionEmployee = getExceptionalById(notFoundId)

  printEmployee(noExceptionEmployee)
  printEmployee(exceptionEmployee)

  /*
   * Option specifies the presence or absence of a value
   *
   * if a value is present, it is Some(value),
   * and if it is absent, then the value is None
   *
   */
  def getById(id: Int): Option[Employee] = {
    employees.find(e => e.id == id)
  }

  /*
   * Either specifies that either there was an error or it was a success
   *
   * it has two values Left and Right
   * by convention, left is an error, and right denotes success
   *
   */
  def getEitherById(id: Int): Either[String, Employee] = {
    val optEmployee = employees.find(_.id == id)

    // a simple, but tedious way of dealing with an option value
    if (optEmployee.isEmpty)
      Left(s"Employee by id: $id not found in the DB.")
    else
      Right(optEmployee.get)
  }

  /*
   * Try can throw an exception along with the successful value
   */
  def getExceptionalById(id: Int): Try[Employee] = {
    val optEmployee = employees.find(_.id == id)

    // simpler and idiomatic way of dealing with an option value
    optEmployee.map(e => Try(e)).getOrElse(throw new Exception(s"Employee by id: $id not found in the DB."))
  }

  def printEmployee(optEmployee: Option[Employee]): Unit = {
    // another tedious way of dealing with an option value
    optEmployee match {
      case Some(e) => println(s"Found employee: $e")
      case None => println(s"Employee by id: $notFoundId not found in the DB.")
    }
  }

  def printEmployee(eitherEmployee: Either[String, Employee]): Unit = {
    // a tedious way of dealing with an either value
    eitherEmployee match {
      case Left(errorStr) => println(s"Not found reason: $errorStr.")
      case Right(emp) => println(s"Found employee: $emp")
    }
  }

  def printEmployee(tryEmployee: Try[Employee]): Unit = {
    // a tedious way of dealing with a try value
    tryEmployee match {
      case Failure(e) => println(s"Some exception occurred: ${e.getMessage}.")
      case Success(emp) => println(s"Found employee: $emp")
    }
  }

  // builds a database of employees
  def getEmployees: Seq[Employee] = {
    Seq(
      Employee(1, "Kuldeep"),
      Employee(2, "Rahul"),
      Employee(3, "Mayank"),
      Employee(4, "Vijay"),
    )
  }
}
