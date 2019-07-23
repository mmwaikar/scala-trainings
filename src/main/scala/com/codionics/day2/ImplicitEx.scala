package com.codionics.day2

import java.time.LocalDate

/* Implicits */

// Implicit conversions are used to convert between types.
// You must import implicit conversions so that they are in scope as single identifiers.
// An implicit parameter list requests objects of a given type. They can be obtained from implicit objects
// that are defined as single identifiers in scope, or from the companion object of the desired type.
// If an implicit parameter is a single-argument function, it is also used as an implicit conversion.
// A context bound of a type parameter requires the existence of an implicit object of the given type.
// If it is possible to locate an implicit object, this can serve as evidence that a type conversion is valid.

object ImplicitEx {

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
