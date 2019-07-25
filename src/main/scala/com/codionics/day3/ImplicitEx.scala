package com.codionics.day3

import java.time.LocalDate

object ImplicitEx {

  /* Implicit parameters */

  def getName(firstName: String, lastName: String)(implicit title: String): String = {
    s"$title. $firstName $lastName"
  }

  implicit val title = "Mr"
  val name = getName("Rohit", "Sharma")
  val name1 = getName("Rohini", "Sharma")("Ms")

  /* Implicit conversions */

  // An implicit function is a function with a single parameter that is
  // declared with the implicit keyword. Implicit functions allow us to
  // define conversions between types.

  implicit def string2Date(dateStr: String): LocalDate = {
    // here we are assuming the date to be in ISO_LOCAL_DATE format i.e. "2016-08-16"
    LocalDate.parse(dateStr)
  }

  // what is happening here? minusDays is a function which works on LocalDate objects not strings???
  // the implicit conversion function turns the String into a LocalDate, and then the minusDays
  // function is called on that LocalDate
  val yesterday = "2019-07-23".minusDays(1)

  // Now if you notice above, we are never calling the function string2Date explicitly,
  // hence the name Implicits. You can give any name to the conversion function.
  // Since you don’t call it explicitly, you may be tempted to use something short such
  // as s2d. But, as you will see later, sometimes it is useful to import a conversion
  // function. So its preferable to stick with the source2target convention.

}
