package com.codionics.day3

import java.time.LocalDate
import java.time.format.DateTimeFormatter

import com.codionics.day3.xmlprocessing.{Address, Customer, Item, PurchaseOrder}
import com.codionics.Utils._

import scala.xml.{Elem, NodeBuffer}

object XmlEx extends App {

  val sampleXml = getSampleXml
  println(sampleXml.toString())

  val poXml = getPOXml
  println(poXml.toString())

  def getPOXml: Elem = {
    val po = getPO
    getPOXml(po)
  }

  private def getPO: PurchaseOrder = {
    val item1 = Item("872-AA", "Lawnmower", 1, 148.95, Option("Confirm this is electric"))
    val item2 = Item("926-AA", "Baby Monitor", 1, 39.98, shipDate = Option(LocalDate.of(1999, 5, 21)))

    val customer1 = Customer("Alice Smith", "US", Address("123 Maple Street", "Mill Valley", "CA", "90952"))
    val customer2 = Customer("Robert Smith", "US", Address("8 Oak Avenue", "Old Town", "PA", "95819"))

    val orderDate = LocalDate.of(1999, 10, 20)
    PurchaseOrder(orderDate, customer1, customer2, "Hurry, my lawn is going wild!", Seq(item1, item2))
  }

  private def getPOXml(po: PurchaseOrder): Elem = {
    <purchaseOrder orderDate={ po.orderDate.toFormat("yyyy-MM-dd") }>
      <shipTo country={po.shipTo.country}>{ getCustomerXml(po.shipTo) }</shipTo>
      <billTo country={po.billTo.country}>{ getCustomerXml(po.billTo) }</billTo>
      <comment>{po.comment}</comment>
      { getItemsXml(po) }
    </purchaseOrder>
  }

  private def getCustomerXml(customer: Customer): NodeBuffer = {
    <name>{customer.name}</name>
    <street>{customer.address.street}</street>
    <city>{customer.address.city}</city>
    <state>{customer.address.state}</state>
    <zip>{customer.address.zip}</zip>
  }

  private def getItemsXml(po: PurchaseOrder): Elem = {
    <items>{ for (item <- po.items) yield getItemXml(item) }</items>
  }

  private def getItemXml(item: Item): Elem = {
    val optionalComment = item.comment.map(c => <comment>{c}</comment>).getOrElse("")
    val optionalDate = item.shipDate.map(d => <shipDate>{d}</shipDate>).getOrElse("")

    <item partNum={item.partNum}>
      <productName>{item.productName}</productName>
      <quantity>{item.quantity}</quantity>
      <USPrice>{item.usaPrice}</USPrice>
      { optionalComment }
      { optionalDate }
    </item>
  }

  private def getDateStr(d: LocalDate): String = {
    val yyyyMMddFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    d.format(yyyyMMddFormat)
  }

  def getSampleXml: Elem = {
    val page =
      <html>
        <head>
          <title>Hello XHTML world</title>
        </head>
        <body>
          <h1>Hello world</h1>
          <p><a href="scala-lang.org">Scala</a> talks XHTML</p>
        </body>
      </html>
    page
  }
}

/*

<?xml version="1.0"?>
<purchaseOrder orderDate="1999-10-20">
  <shipTo country="US">
    <name>Alice Smith</name> <street>123 Maple Street</street>
    <city>Mill Valley</city> <state>CA</state> <zip>90952</zip>
  </shipTo>
  <billTo country="US">
    <name>Robert Smith</name> <street>8 Oak Avenue</street>
    <city>Old Town</city> <state>PA</state> <zip>95819</zip>
  </billTo>
  <comment>Hurry, my lawn is going wild!</comment>
  <items>
    <item partNum="872-AA">
      <productName>Lawnmower</productName> <quantity>1</quantity>
      <USPrice>148.95</USPrice> <comment>Confirm this is electric</comment>
    </item>
    <item partNum="926-AA">
      <productName>Baby Monitor</productName> <quantity>1</quantity>
    <USPrice>39.98</USPrice> <shipDate>1999-05-21</shipDate>
    </item>
  </items>
</purchaseOrder>

 */

/*

<html>
  <head>
    <title>Hello XHTML world</title>
  </head>
  <body>
    <h1>Hello world</h1>
    <p><a href="http://scala-lang.org/">Scala</a> talks XHTML</p>
  </body>
</html>

 */