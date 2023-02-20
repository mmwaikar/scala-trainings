import scala.xml.{Elem, Node}

val doc = <html><head><title>Fred's Memoirs</title></head></html>
val items = <li>Fred</li><li>Wilma</li>

val elem = <a href="http://scala-lang.org">The <em>Scala</em> language</a>
elem.attributes("href")
elem.attributes("href").text

elem.attributes.get("href")
elem.attributes.get("href").getOrElse("")

val names = Seq("Kuldeep", "Jasprit")
val oneWay = <ul><li>{names(0)}</li><li>{names(1)}</li></ul>
val anotherWay = <ul>{ for (i <- items) yield <li>{i}</li>}</ul>

val list = <dl><dt>Java</dt><dd>Gosling</dd><dt>Scala</dt><dd>Odersky</dd></dl>
val languages = list \\ "dt"

println(s"label: ${doc.label}")
println(s"children: ${doc.child}")
println(s"head: ${doc.head}")
println(s"tail: ${doc.tail}")

val a = <a href="http://scala-lang.org">The Scala language</a>
val url: collection.Seq[Node] = a.attributes("href")