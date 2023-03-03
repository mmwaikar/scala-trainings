# Introduction to Scala

Scala is a portmanteau of **sca**lable and **la**nguage, signifying that it is designed to grow with the demands of its 
users, and that you can write scalable software with it.

## Advantages of Scala

**Scala combines object-oriented (OO) and functional programming (FP) in one concise, high-level language.** Although, 
it mentions OO before FP, the true power of Scala comes due to it's support of FP concepts (Java has already adopted a
lot of Scala concepts now).

## Scala (is a much better Java)

- It has a REPL

- It is concise, with much better type inference

```scala
val x = 2                                     // the compiler infers the type of x to be of type Int
val aSeq = Seq("a", "b", "c")                 // aSeq is of type Seq[String]
val aMap = Map("a" -> 1, "b" -> 2, "c" -> 3)  // aMap is of type Map[String, Int]

// the return type is inferred to be of type Int (don't need to specify explicitly)
// similarly, no return keyword is required (the last statement is the return value)
def add(a: Int, b: Int) = a + b
```
- Immutability (of data and collections)

- Value Equality (records in Java)

- Functions are first class citizens (which means, a function does not have to be a part of a class)

- No statements, only expressions (e.g. if-else construct)

- Is a mixed OO, FP language (with features such as including currying, type inference, immutability, 
  lazy evaluation, and pattern matching)
   
- `object HelloWorld extends App {
		println("Hello, World!")
	}` 
	(compile with scalac and run with scala)

### sbt

[sbt](https://www.scala-sbt.org/) is an interactive build tool for Scala (& Java). You can scaffold new projects, run 
tests / code and or generate builds etc.
