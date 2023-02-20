package com.codionics.day3.xmlprocessing

import scala.xml.{Attribute, Elem, Node, NodeBuffer, NodeSeq, Null, Text}

object XmlEx {

  /* XML Literals */

  // Scala has built-in support for XML. You can define XML literals, simply by using the XML code:
  val doc: Elem = <html><head><title>Hello XML</title></head></html>
  println(s"Label: ${doc.label}") // the label property yields the tag name, hence, prints html

  val children: Seq[Node] = doc.child
  println(s"Children: $children")

  // An XML literal can also be a sequence of nodes.
  val items: NodeBuffer = <li>Fred</li><li>Wilma</li>

  /* XML Nodes */

  // The Node class is the ancestor of all XML node types. Its two most important subclasses are Text and Elem.

  // If you build node sequences programmatically, you can use a NodeBuffer, a subclass of ArrayBuffer[Node].

  val ul = new NodeBuffer
  ul += <li>Fred</li>
  ul += <li>Wilma</li>
  val nodes: NodeSeq = ul

  // A NodeBuffer is a Seq[Node]. It can be implicitly converted to a NodeSeq. Once this conversion
  // has occurred, you need to be careful not to mutate the node buffer any longer since XML node
  // sequences are supposed to be immutable.

  /* Element Attributes */

  // To process the attribute keys and values of an element, use the attributes property. It yields
  // an object of type MetaData which is almost, but not quite, a Map from attribute keys to values.
  // You can use the () operator to access the value for a given key:
  val a = <a href="http://scala-lang.org">The Scala language</a>
  val url: collection.Seq[Node] = a.attributes("href") // unfortunately this yields a node sequence

  // you can simply call the text method to turn the node sequence into a string:
  val urlString = a.attributes("href").text

  // If an attribute is not present, the () operator returns null. If you dislike working with null,
  // use the get method, which returns an Option[Seq[Node]].
  val optionUrls: Option[collection.Seq[Node]] = a.attributes.get("href")
  val urlText = optionUrls.getOrElse(Text(""))

  /* Embedded expressions */

  // You can include blocks of Scala code inside XML literals to dynamically compute items.
  val brands = Seq("Lee", "Wrangler")
  val brandsXml = <ul><li>{brands.head}</li></ul>

  /* Expressions in attributes */

  // You can compute attribute values with Scala expressions, e.g. XmlExPO.getPOXml method.

  /* XPath like expressions */

  // The NodeSeq class provides methods that resemble the / and // operators in XPath (XML Path
  // Language). Since // denotes comments and is therefore not a valid operator, Scala uses \ and \\
  // instead. The \ operator locates direct descendants of a node or node sequence.

  val list =
    <dl><dt>Java</dt><dd>Gosling</dd><dt>Scala</dt><dd>Odersky</dd></dl>
  val languages = list \ "dt"

  // A wildcard matches any element. For example,
  doc \ "body" \ "_" \ "li"

  // finds all li elements, whether they are contained in a ul, an ol, or any other element inside
  // the body. The \\ operator locates descendants at any depth. For example,

  doc \\ "img" // locates all img elements anywhere inside the doc. A string starting with @ locates attributes. For ex,
  a \ "@href" // returns the value of the href attribute of the given node, and
  doc \\ "@alt" // locates all alt attributes of any elements inside doc

  // The result of \ or \\ is a node sequence. It might be a single node, but unless you know
  // that for sure, you should traverse the sequence. If you simply call text on a result of \
  // or \\, all texts of the result sequence will be concatenated.

  /* Pattern matching */

  // You can use XML literals in pattern matching expressions. For example,
  doc match {
    case <img/> => println(s"image node found")
    case _      => println(s"any other node")
  }

  // The first match succeeds if node is an img element with any attributes and no child elements.
  // To deal with child elements is a little tricky. You can match a single child with
  doc match {
    case <li>{_}</li> => println(s"single child")
  }

  // However, if li has more than one child, for example <li>An <em>important</em> item</li>, then
  // the match fails. To match any number of items, use
  doc match {
    case <li>{_*}</li> => println(s"many children")
  }

  // Instead of the wildcard indicators, you can use variable names. The match is bound to the variable.
  doc match {
    case <li>{child}</li> => child.text
  }

  // To match a text node, use a case class match, like this:
  doc match {
    case <li>{Text(item)}</li> => item
  }

  // To bind a variable to a node sequence, use the following syntax:
  doc match {
    case <li>{children @ _*}</li> => for (c <- children) yield c
  }
  
  /* Modifying elements and attributes */

  // In Scala, XML nodes and node sequences are immutable. If you want to edit a node, you have to
  // create a copy of it, making any needed changes and copying what hasn’t changed. To copy an
  // Elem node, use the copy method. It has five named parameters: the familiar label, attributes,
  // and child, as well as prefix and scope which are used for namespaces. Any parameters that you
  // don’t specify are copied from the original element.
  val unsignedNames = <ul><li>Fred</li><li>Wilma</li></ul>
  val orderedNames = unsignedNames.copy(label = "ol")

  // to add a child
  val addedNames = unsignedNames.copy(child = unsignedNames.child ++ <li>Puma</li>)

  // To add or change an attribute, use the % operator - the first argument is the namespace.
  // The last one is a list of additional metadata.
  val image = <img src="hamster.jpg"/>
  val image2 = image % Attribute(null, "alt", "An image of a hamster", Null)

  // To add more than one attribute, you can chain them like this:
  val image3 = image % Attribute(null, "alt", "An image of a frog",
    Attribute(null, "src", "frog.jpg", Null))

  // Adding an attribute with the same key replaces the existing one. The image3 element
  // has a single attribute with key "src"; its value is "frog.jpg".
}
