package com.codionics.day3.xmlprocessing

import java.io.FileInputStream
import java.time.LocalDate

import com.codionics.Utils._

import scala.xml.{Elem, NodeBuffer, XML}

object XmlExPO extends App {

  val sampleXml = getSampleXml
  println(sampleXml.toString())

  val poXml = getPOXml
  println(poXml.toString())

  def getPOXml: Elem = {
    val po = getPO
    getPOXml(po)
  }

  // save as an XML file
  val xmlFileName = "PurchaseOrder.xml"
  XML.save(xmlFileName, poXml, enc = "UTF-8", xmlDecl = true)

  // load an XML file
  val poXmlFromFile = XML.load(new FileInputStream(xmlFileName))
  println(s"PO XML from file: ${poXmlFromFile.toString()}")

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

//  private def getDateStr(d: LocalDate): String = {
//    val yyyyMMddFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    d.format(yyyyMMddFormat)
//  }

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
