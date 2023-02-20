package com.codionics.day1.functions

import java.time.LocalDate

/*
 * https://lukajcb.github.io/blog/scala/2016/03/08/a-real-world-currying-example.html
 *
 * Let’s imagine we wanted a program that deals with premiums for credit card usage.
 * What we have is a list of credit cards and we’d like to calculate the premiums
 * for all those cards that the credit card company has to pay out. The premiums
 * themselves depend on the total number of credit cards, so that the company adjust
 * them accordingly.
 */
case class CreditCardInfo(dateIssued: LocalDate)

case class Person(name: String)

case class CreditCard(creditInfo: CreditCardInfo, issuer: Person)

object CreditCard {

  def getCreditCards: Seq[CreditCard] = Seq.empty[CreditCard]

  /**
    * We already have a function that calculates the premium for a single credit card
    * and takes into account the total cards the company has issued.
    *
    * @param totalCards
    * @param creditCard
    * @return
    */
  def getPremiumProblematic(totalCards: Int, creditCard: CreditCard): Double = ???

  /**
    * We can partially apply the total number of credit cards and use that function to
    * map the credit cards to their premiums. All we need to do is curry the getPremium
    * function by changing it to use multiple parameter lists and we’re good to go.
    *
    * @param totalCards
    * @param creditCard
    * @return
    */
  def getPremium(totalCards: Int)(creditCard: CreditCard): Double = ???
}

object CurryingEx {

  import CreditCard._

  val creditCards: Seq[CreditCard] = getCreditCards

  // Now a reasonable approach to this problem would be to map each credit card to a
  // premium and reduce it to a sum. Something like below (however the compiler isn’t
  // going to like this, because CreditCard.getPremium requires two parameters) -

  // val allPremiumsWontCompile = creditCards.map(getPremiumProblematic).sum

  val getPremiumWithTotal: CreditCard => Double = getPremium(creditCards.length)

  val allPremiums: Double = creditCards.map(getPremiumWithTotal).sum
}
