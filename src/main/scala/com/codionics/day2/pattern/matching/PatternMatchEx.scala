package com.codionics.day2.pattern.matching

object PatternMatchEx {

  val maleEnglish = getGreeting("M", "Bob", "English")
  val femaleHindi = getGreeting("F", "Anu", "Hindi")
  val femaleMarathi = getGreeting("F", "Veena", "Marathi")

  def getGreeting(sex: String, name: String, language: String): String = {

    val salutation = sex match {
      case "M" => "Mr."
      case "F" => "Ms."
    }

    val greeting = language match {
      case "English" => "Hello"
      case "Hindi" => "Namaste"
      case "Marathi" => "Namaskar"
    }

    s"$greeting $salutation $name, how are you today?"
  }
}
