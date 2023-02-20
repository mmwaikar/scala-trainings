package com.codionics.day1.functions

import org.scalatest.{FlatSpec, Matchers}

/*
 * http://www.scalatest.org/ (similar to JUnit but much more powerful)
 * TDD - Test Driven Development
 * BDD - Behavior Driven Development
 *
 * The central concept in ScalaTest is the suite, a collection of zero to many tests.
 *
 * ScalaTest supports different testing styles -
 *
 * We recommend you select one main style for unit testing and another for acceptance testing.
 * Using a different style for unit and acceptance testing can help developers "switch gears"
 * between low-level unit testing to high-level acceptance testing.
 *
 * FunSuite - For teams coming from xUnit, FunSuite feels comfortable and familiar
 * while still giving some of the benefits of BDD: FunSuite makes it easy to write
 * descriptive test names, natural to write focused tests, and generates specification-like
 * output that can facilitate communication among stakeholders.
 *
 * FlatSpec - A good first step for teams wishing to move from xUnit to BDD, FlatSpec's
 * structure is flat like xUnit, so simple and familiar, but the test names must be
 * written in a specification style: "X should Y," "A must B," etc.
 *
 * FunSpec - For teams coming from Ruby's RSpec tool, FunSpec will feel very familiar;
 * More generally, for any team that prefers BDD, FunSpec's nesting and gentle guide to
 * structuring text (with describe and it) provides an excellent general-purpose choice
 * for writing specification-style tests.
 *
 * WordSpec - For teams coming from specs or specs2, WordSpec will feel familiar, and
 * is often the most natural way to port specsN tests to ScalaTest. WordSpec is very
 * prescriptive in how text must be written, so a good fit for teams who want a high
 * degree of discipline enforced upon their specification text.
 *
 * FreeSpec - Because it gives absolute freedom (and no guidance) on how specification
 * text should be written, FreeSpec is a good choice for teams experienced with BDD
 * and able to agree on how to structure the specification text.
 *
 * FeatureSpec - is primarily intended for acceptance testing, including facilitating the
 * process of programmers working alongside non-programmers to define the acceptance requirements.
 *
 * BeforeAndAfterAll - beforeAll method that will be run before all tests,
 * and an afterAll method that will be run after all tests.
 *
 * BeforeAndAfterEach - beforeEach (like JUnit's setUp) and afterEach (like JUnit's tearDown)
 *
 * http://www.scalatest.org/user_guide/using_matchers
 * ScalaTest provides a domain specific language (DSL) for expressing assertions in tests using
 * the word should. Just mix in Matchers,
 *
 */

class FunctionsExSpec extends FlatSpec with Matchers with FunctionsEx {

  "A FunctionsEx" should "multiply any number by 2" in {
    val six = multiplyByTwo(3)
    six should be(6)
  }

  it should "add two to any number" in {
    val three = addTwo(1)
    three should not be 0
    three should be(3)
  }
  
  it should "greet a female" in {
    val greeting = greet("Simona", "F")
    greeting should not be null
    greeting should not be empty
    greeting should include("Ms.")
  }

  it should "greet a male" in {
    val greeting = greet("Roger")
    greeting should not be null
    greeting should not be empty
    greeting should include("Mr.")
  }
}
