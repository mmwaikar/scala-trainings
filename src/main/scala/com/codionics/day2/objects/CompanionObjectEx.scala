package com.codionics.day2.objects

// In Java or C++, you often have a class with both instance methods and static methods.
// In Scala, you achieve this by having a class and a “companion” object of the same name.
// The class and its companion object can access each other’s private features.
// They must be located in the same source file.

class CompanionObjectEx(firstName: String, lastName: String) {

  // The companion object of a class is accessible, but it is not in scope.
  // Hence we have to import it or use ObjectName.functionName notation.
  import CompanionObjectEx._

  val name = CompanionObjectEx.getFullName(firstName, lastName)

  val nameOtherFormat = getFullNameOtherFormat(firstName, lastName)
}

object CompanionObjectEx {

  def getFullName(firstName: String, lastName: String): String = {
    s"$firstName $lastName"
  }

  def getFullNameOtherFormat(firstName: String, lastName: String): String = {
    s"$lastName, $firstName"
  }
}