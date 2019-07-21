package com.codionics.day4.actors

object DslEx {

  /* What is a DSL */

  // A DSL is a programming language that’s targeted at a specific problem; other programming
  // languages that you use are more general purpose. It contains the syntax and semantics
  // that model concepts at the same level of abstraction that the problem domain offers. For
  // example, when you order your Cinnamon Latte, you use the domain language that the barista
  // readily understands.

  // The two most important qualities of a DSL that you need to remember are:
  // A DSL is targeted at a specific problem area.
  // A DSL contains syntax and semantics that model concepts at the same level of abstraction as
  // the problem domain does.

  // The key issue is communication, the ability for your program to communicate with its intended
  // audience. In the case of a DSL, the direct audience is neither the compiler nor the CPU, but
  // the human minds that need to understand its behavior. The language needs to be communicative
  // to its audience and allow code snippets that are expressive enough to map to the thought
  // process of the domain modeler. For this to happen, the DSL that you design has to offer the
  // correct level of syntactic as well as semantic abstractions to the user.

  /* Popular DSLs in use */

  // DSL                                      Used for
  // SQL                  Relational database language used to query and manipulate data
  // Ant, Rake, Make      Languages for building software systems
  // CSS                  Stylesheet description language
  // YACC, Bison, ANTLR   Parser-generator languages
  // RSpec, Cucumber      Behavior-driven testing language in Ruby
  // HTML                 Markup language for the web

  // There are a lot more DSLs that you use on a regular basis. Can you identify some of the common
  // characteristics that these languages have? Here are a few:
  //
  // All DSLs are specific to the domain. Each language is of limited expressivity; you can use a DSL
  // to solve the problem of that particular domain only. You can’t build cargo management systems
  // using only HTML.
  //
  // Every DSL is expressive enough to make its intentions clear to the nonprogramming user. The DSL
  // isn’t merely a collection of APIs that you use; every API is concise and speaks the vocabulary
  // of the domain.
  //
  // For every DSL, you can go back to your source file months after you wrote them and immediately
  // be able to figure out what you meant.

  // Consider Ant, the popular build tool, and the XML-based DSL that it presents to the user. As a
  // developer, when you look at the following XML snippet in Ant, you’ll find that it expresses
  // familiar concepts. The code clearly spells out that it’ll build a jar as the target and that
  // this task has a dependency on the task compile.

  /*
    <target name="jar" depends="compile">
      <mkdir dir="${build.dist}"/>
      <jar jarfile="${build.dist}/${name}-${version}.jar">
        <fileset dir="${build.classes}" includes="**"/>
        <fileset dir="${src.dir}">
          <include name="*"/>
        </fileset>
      </jar>
    </target>
   */

  /* Classifying DSLs */

  // A DSL speaks the language of the domain. The richer the domain, the more expressive the DSL needs
  // to be. To the domain user, a DSL makes him understand the story of the domain that the developers
  // have implemented as the underlying model. It doesn’t matter to him how the underlying model has
  // been implemented, so long as he has coherent access to the domain abstractions through the DSL script.
  // The most popular way to classify DSLs is related to the way you implement them. Martin Fowler made
  // this broad classification some time back and it’s recognized and followed by almost all practitioners
  // in the industry today. He classifies a DSL as internal or external, depending on whether it’s been
  // implemented on top of an existing host language.
  //
  // Internal DSLs are also known as embedded DSLs because they’re implemented as an embedding within a
  // host language.
  //
  // External DSLs are also called standalone DSLs because they’re developed ground-up as an independent
  // language, without using the infrastructure of an existing host language.

  /* Internal DSLs */

  // An internal DSL is one that uses the infrastructure of an existing programming language (also called
  // the host language of the DSL) to build domain-specific semantics on top of it. One of the most popular
  // internal DSLs used today is Rails, which is implemented on top of the Ruby programming language. When
  // you write Rails code, you’re programming in Ruby, based on the semantics that Rails implements for
  // developing web applications. In most cases, an internal DSL is implemented as a library on top of the
  // existing host language.

  /* External DSLs */

  // An external DSL is one that’s developed ground-up and has separate infrastructure for lexical analysis,
  // parsing techniques, interpretation, compilation, and code generation. Developing an external DSL is
  // similar to implementing a new language from scratch with its own syntax and semantics. Build tools like
  // make, parser generators like YACC, and lexical analysis tools like LEX are examples of popular external DSLs.
  
  /* Order processing DSL */

  /*
    place orders (
      new Order to buy(100 sharesOf "IBM")
        limitPrice 300
        allOrNone
        using premiumPricing,
      new Order to buy(200 sharesOf "CISCO")
        limitOnClosePrice 300
        using premiumPricing,
      new Order to buy(200 sharesOf "GOOGLE")
        limitOnOpenPrice 300
        using defaultPricing,
      new Order to sell(200 bondsOf "SUN")
        limitPrice 300
        allOrNone
      using {
        (qty, unit) => qty * unit - 500
      }
    )
   */
}
