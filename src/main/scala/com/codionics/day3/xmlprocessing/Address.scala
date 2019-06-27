package com.codionics.day3.xmlprocessing

import java.time.LocalDate

case class Address (street: String, city: String, state: String, zip: String)

case class Item(partNum: String, productName: String, quantity: Int, usaPrice: Double,
                comment: Option[String] = None,
                shipDate: Option[LocalDate] = None)

case class Customer(name: String, country: String, address: Address)

case class PurchaseOrder(orderDate: LocalDate, shipTo: Customer, billTo: Customer, comment: String, items: Seq[Item])
