package com.codionics

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Utils {

  implicit class LocalDateOps(d: LocalDate) {

    def toFormat(pattern: String): String = {
      val format = DateTimeFormatter.ofPattern(pattern)
      d.format(format)
    }
  }
}
