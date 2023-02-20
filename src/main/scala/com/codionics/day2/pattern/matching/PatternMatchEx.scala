package com.codionics.day2.pattern.matching

/*
 * Scala has a powerful pattern matching mechanism that has a number of applications:
 * switch statements, type inquiry, and “destructuring” (getting at the parts of
 * complex expressions).
 *
 * The match expression is a better switch, without fall-through.
 * If no pattern matches, a MatchError is thrown. Use the case _ pattern to avoid that.
 * A pattern can include an arbitrary condition, called a guard.
 * You can match on the type of an expression; prefer this over isInstanceOf/asInstanceOf.
 * You can match patterns of arrays, tuples, and case classes, and bind parts of the pattern to variables.
 * In a for expression, nonmatches are silently skipped.
 * A case class is a class for which the compiler automatically produces the methods that are needed for pattern matching.
 * The common superclass in a case class hierarchy should be sealed.
 *
 * Unlike the switch statement, Scala pattern matching does not suffer from the “fall-through” problem.
 */

object PatternMatchEx {

  val maleEnglish = getGreeting("M", "Bob", "English")
  val femaleHindi = getGreeting("F", "Anu", "Hindi")
  val femaleMarathi = getGreeting("F", "Veena", "Marathi")

  def getGreeting(sex: String, name: String, language: String): String = {

    // Notice that similar to if, match is an expression, not a statement,
    // the value of the below match is stored in the salutation variable
    val salutation = sex match {
      case "M" => "Mr."
      case "F" => "Ms."
    }

    // You can use the match statement with any types, not just strings / numbers.
    val greeting = language match {
      case "English" => "Hello"
      case "Hindi"   => "Namaste"
      case "Marathi" => "Namaskar"
    }

    s"$greeting $salutation $name, how are you today?"
  }

  /* Guards */

  // Suppose we want to extend our example to match all digits. In a C-style switch
  // statement, you would simply add multiple case labels, for example case '0': case '1': ... case '9':.
  // (Except that, of course, you can’t use ... but must write out all ten cases explicitly.)
  // In Scala, you add a guard clause to a pattern, like this:
  val ch: Char = '*'
  var sign, digit = 0

  ch match {
    case '+'                        => sign = 1
    case '-'                        => sign = -1
    case _ if Character.isDigit(ch) => digit = Character.digit(ch, 10)
    case _                          => sign = 0
  }

  // The guard clause can be any Boolean condition. Note that patterns are always matched
  // top-to-bottom. If the pattern with the guard clause doesn’t match, the catch-all
  // pattern is attempted.

  /* Variables in patterns */

  // If the case keyword is followed by a variable name, then the match expression
  // is assigned to that variable. For example, sign and digit above.

  /* Type patterns */

  // You can match on the type of an expression -
  val obj: Object = null

  // In Scala, this form is preferred over using the isInstanceOf operator.
  obj match {
    case s: String => Integer.parseInt(s)
    case _: BigInt => Int.MaxValue
    case _         => 0
  }

  // Note the variable names in the patterns. In the first pattern, the match is bound to x as an
  // Int, and in the second pattern, it is bound to s as a String. No asInstanceOf casts are needed!

  /* Matching Arrays, Lists, and Tuples */

  // To match an array against its contents, use Array expressions in the patterns, like this:
  val arr = Array(0, 1)

  arr match {
    case Array(0)     => "0"
    case Array(x, y)  => x + " " + y
    case Array(0, _*) => "0 ..."
    case _            => "something else"
  }

  // The first pattern matches the array containing 0. The second pattern matches any array with
  // two elements, and it binds the variables x and y to the elements. The third pattern matches
  // any array starting with zero. You can match lists in the same way, with List expressions.
  // Alternatively, you can use the :: operator:
  val lst = List(0, 1)

  lst match {
    case 0 :: Nil      => "0"
    case x :: y :: Nil => x + " " + y
    case 0 :: tail     => "0 ..."
    case _             => "something else"
  }

  // With tuples, use the tuple notation in the pattern:
  val pair = (0, 1)

  pair match {
    case (0, _) => "0 ..."
    case (y, 0) => y + " 0"
    case _      => "neither is 0"
  }

  // Again, note how the variables are bound to parts of the list or tuple. Since these bindings
  // give you easy access to parts of a complex structure, this operation is called destructuring.

  /* Extractors */

  // In the preceding section, you have seen how patterns can match arrays, lists, and tuples.
  // These capabilities are provided by extractors—objects with an unapply or unapplySeq method
  // that extract values from an object. The unapply method is provided to extract a fixed
  // number of objects, while unapplySeq extracts a sequence whose length can vary. The Array
  // companion object is an extractor — it defines an unapplySeq method. That method is called
  // with the expression that is being matched, not with what appears to be the parameters in
  // the pattern. The call Array.unapplySeq(arr) yields a sequence of values, namely the values
  // in the array. The first value is compared with zero, and the second one is assigned to x.

  /* Patterns in Variable Declarations */

  val (x, y) = (1, 2)
  val Array(first, second, _*) = arr
}
