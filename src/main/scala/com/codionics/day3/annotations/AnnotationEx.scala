package com.codionics.day3.annotations

import java.io.IOException
import java.util

import com.sun.istack.internal.NotNull
import javax.persistence.{Entity, Id}
import org.junit.Test

import scala.annotation.varargs
import scala.beans.BeanProperty

/*
 * Annotations let you add information to program items. This information can be
 * processed by the compiler or by external tools.
 *
 * You can annotate classes, methods, fields, local variables, parameters, expressions,
 * type parameters, and types.
 * With expressions and types, the annotation follows the annotated item.
 * Annotations have the form @Annotation, @Annotation(value), or @Annotation(name1 = value1, ...).
 * @volatile, @transient, @strictfp, and @native generate the equivalent Java modifiers.
 * Use @throws to generate Java-compatible throws specifications.
 * The @tailrec annotation lets you verify that a recursive function uses tail call optimization.
 * The assert function takes advantage of the @elidable annotation. You can optionally
 * remove assertions from your Scala programs.
 * Use the @deprecated annotation to mark deprecated features.
 *
 */
object AnnotationEx {

  /* What are annotations? */

  // Annotations are tags that you insert into your source code so that some tools can
  // process them. These tools can operate at the source level, or they can process the
  // class files into which the compiler has placed your annotations. Annotations are
  // widely used in Java, for example by testing tools such as JUnit 4.

  @Test def testSomeFeature() = ???

  // You can use Java annotations with Scala classes. The annotation in the preceding
  // example is from JUnit, a Java framework that have no particular knowledge of Scala.
  // You can also use Scala annotations. These annotations are specific to Scala and
  // are usually processed by the Scala compiler or a compiler plugin. Java annotations
  // do not affect how the compiler translates source code into bytecode; they merely
  // add data to the bytecode that can be harvested by external tools. In Scala,
  // annotations can affect the compilation process. For example, the @BeanProperty
  // annotation causes the generation of getter and setter methods.

  /* What can be annotated? */

  // In Scala, you can annotate classes, methods, fields, local variables, and parameters,
  // just like in Java. If the annotation has no arguments, the parentheses can be omitted.
  @Entity class Credentials

  @BeanProperty @Id var username = null

  def doSomething(@NotNull message: String) = ???

  // When annotating the primary constructor, place the annotation before the constructor,
  // and add a set of parentheses if the annotation has no arguments. For ex,

  // class Credentials @Inject() (var username: String, var password: String)

  /* Annotation arguments */

  // Java annotations can have named arguments, such as
  @Test(timeout = 100, expected = classOf[IOException])
  def anotherTest = ???

  // Arguments of Java annotations are restricted to a few types:
  // Numeric literals, Strings, Class literals, Java enumerations, Other annotations, Arrays
  // of the above (but not arrays of arrays)
  //
  // Arguments of Scala annotations can be of arbitrary types, but only a couple of the Scala
  // annotations take advantage of this added flexibility. For instance, the @deprecatedName
  // annotation has an argument of type Symbol.

  /* Annotations for Java Features */

  // The Scala library provides a number of annotations for interoperating with Java.
  // The @volatile annotation marks a field as volatile (a volatile field can be updated in multiple threads):
  @volatile var done = false

  // The @transient annotation marks a field as transient (a transient field is not serialized):
  @transient var recentLookups = new util.HashMap[String, String]

  /* Checked exceptions */

  // Unlike Scala, the Java compiler tracks checked exceptions. If you call a Scala method
  // from Java code, its signature should include the checked exceptions that can be thrown.
  // Use the @throws annotation to generate the correct signature.

  class Book {
    @throws(classOf[IOException]) def read(filename: String) = ???
  }
  // The Java signature is - void read(String filename) throws IOException
  // Without the @throws annotation, the Java code would not be able to catch the exception.

  /* Variable arguments */

  // The @varargs annotation lets you call a Scala variable-argument method from Java.
  // By default, if you supply a method such as
  def process(args: String*) = ???

  // the Scala compiler translates the variable argument into a sequence def process(args: Seq[String])
  // That is cumbersome to use in Java. If you add @varargs,
  @varargs def processVarArgs(args: String*) = ???

  // then a Java method void process(String... args) is generated that wraps the args array
  // into a Seq and calls the Scala method.

  // check out the @tailrec annotation
}
