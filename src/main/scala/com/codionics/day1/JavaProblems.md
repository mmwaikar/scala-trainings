# Java and the JVM

## Java (the language)

An OO language which debuted in 1995. Had a GC (garbage collector). Made it very easy to write enterprise scale 
software. However, J2EE made people switch to other platforms (like RoR - Ruby on Rails).

## JVM (the platform)

A Java virtual machine is a virtual machine that enables a computer to run Java programs as well as programs written in 
other languages that are also compiled to Java bytecode. The JVM is detailed by a specification that formally describes 
what is required in a JVM implementation.

## Other languages on the JVM

Scala, Clojure and Groovy are a few other languages which run on JVM. *So what makes JVM hugely popular?* The sheer
number of Java libraries. It's said that **"If there is a problem, it has been solved in Java, *twice*."**

### Problems with Java

 - No REPL
 
 - Verbose ( `Map<String, String> map = new HashMap<>();` instead of `var map = new HashMap<String, String>();` )
 
 - Cancer of semicolons
 
 - No value equality (who cares about {or uses} ~~Reference equality~~)
 
 - No immutability / immutable collections (which means, less thread safety, all the cores of the machine cannot be used)
 
 - The language does not promote side-effect free coding practices
 
 - No map, reduce, filter kind of methods on collections (before Java 8)
   { Even with Java 8 streams: `list.stream().map(x -> x * 2).collect(Collectors.toList);` }
   
 - Functions were not first class citizens
 
 - No string interpolation
 `String name = "hello";
  System.out.println(String.format("%s world!", name));` VS. `val name = "hello" println(s"$name world!")`
  
## Scala (a much better Java)

 - Is a mixed OO, Functional programming language (with features such as including currying, type inference, immutability, 
   lazy evaluation, and pattern matching)
   
 - no return keyword (last statement is the return value)
   
 - `object HelloWorld extends App {
		println("Hello, World!")
	}` 
	(compile with scalac and run with scala)
 