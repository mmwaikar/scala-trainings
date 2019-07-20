package com.codionics.day3.concurrency

import scala.concurrent.{ExecutionContext, Future}
import scala.util.{Failure, Success}
//import scala.concurrent.ExecutionContext.Implicits.global

// Assume we have an API for interfacing with a currency trading service.
class CurrencyTradingService {

  def getCurrentValue(symbol: String): Double = ???

  def buy(number: Int, value: Double): Int = ???

  def isProfitable(currencyValue: Double, anotherCurrencyValue: Double): Boolean = {
    currencyValue > anotherCurrencyValue
  }
}

/*
 * https://docs.scala-lang.org/overviews/core/futures.html
 *
 * Futures provide a way to reason about performing many operations in parallel –
 * in an efficient and non-blocking way. A Future is a placeholder object for a
 * value that may not yet exist. Generally, the value of the Future is supplied
 * concurrently and can subsequently be used. Composing concurrent tasks in this
 * way tends to result in faster, asynchronous, non-blocking parallel code.
 *
 * By default, futures and promises are non-blocking, making use of callbacks
 * instead of typical blocking operations.
 */

/*
 * A Future is an object holding a value which may become available at some point. This value is usually
 * the result of some other computation:
 *
 * If the computation has not yet completed, we say that the Future is not completed.
 * If the computation has completed with a value or with an exception, we say that the Future is completed.
 *
 * Completion can take one of two forms:
 *
 * When a Future is completed with a value, we say that the future was successfully completed with that value.
 * When a Future is completed with an exception thrown by the computation, we say that the Future was failed with that exception.
 *
 * A Future has an important property that it may only be assigned once. Once a Future object is
 * given a value or an exception, it becomes in effect immutable– it can never be overwritten.
 */

object FuturesEx {

  // suppose we have a function (getName) which takes a long time to complete
  def getName: String = ???

  // now, if we call this getName function from the main thread, it will hang the
  // UI. Or from wherever it is called, the code execution won't proceed until this
  // function has finished executing, so it is better to have such functions return
  // a Future[_] (a future of something)
  def getNameFromDB: Future[String] = ???

  /* Execution Context */

  /*
   * Futures and Promises revolve around ExecutionContexts, responsible for executing computations.
   * You can think of execution contexts as thread pools.
   *
   * An ExecutionContext is similar to an Executor: it is free to execute computations in a new
   * thread, in a pooled thread or in the current thread (although executing the computation in the
   * current thread is discouraged – more on that below).
   *
   * The scala.concurrent package comes out of the box with an ExecutionContext implementation, a
   * global static thread pool. It is also possible to convert an Executor into an ExecutionContext.
   * Finally, users are free to extend the ExecutionContext trait to implement their own execution
   * contexts, although this should only be done in rare cases.
   */

  val executionContext: ExecutionContext = ExecutionContext.global

  // A typical future looks like this:
  val futureName: Future[String] = Future {
    getName
  }(executionContext)

  // Or with the more idiomatic:
  implicit val ec: ExecutionContext = ExecutionContext.global

  val futureName1: Future[String] = Future {
    getName
  }

  /* The Global Execution Context */

  /*
   * ExecutionContext.global is an ExecutionContext backed by a ForkJoinPool. It should be sufficient
   * for most situations but requires some care. A ForkJoinPool manages a limited number of threads
   * (the maximum number of threads being referred to as parallelism level). By default the
   * ExecutionContext.global sets the parallelism level of its underlying fork-join pool to the number
   * of available processors (Runtime.availableProcessors).
   */
  val indianPlayers = Seq("Virat", "Rahul", "Rohit")

  // suppose we are getting this list from the DB or calling an API
  val futurePlayers = Future { indianPlayers } // Future.apply method is called

  /* Callbacks */

  /*
   * How to use the result once it becomes available, so that we can do something useful with it?
   * From a performance point of view a better way to do it is in a completely non-blocking way,
   * by registering a callback on the future. This callback is called asynchronously once the future
   * is completed. If the future has already been completed when registering the callback, then the
   * callback may either be executed asynchronously, or sequentially on the same thread.
   *
   * The most general form of registering a callback is by using the onComplete method, which
   * takes a callback function of type Try[T] => U. The callback is applied to the value of type
   * Success[T] if the future completes successfully, or to a value of type Failure[T] otherwise.
   */
  futurePlayers onComplete {
    case Success(players) => for (player <- players) println(player)
    case Failure(t) => println("An error has occurred: " + t.getMessage)
  }

  /*
   * The onComplete method is general in the sense that it allows the client to handle the result
   * of both failed and successful future computations. In the case where only successful results
   * need to be handled, the foreach callback can be used:
   */
  futurePlayers foreach { players =>
    for (player <- players) println(player)
  }

  // There is one slight drawback of using callbacks or the foreach method - The onComplete and foreach
  // methods both have result type Unit, which means invocations of these methods cannot be chained.

  /*
   * That said, we should now comment on when exactly the callback gets called. Since it requires the
   * value in the future to be available, it can only be called after the future is completed. However,
   * there is no guarantee it will be called by the thread that completed the future or the thread which
   * created the callback. Instead, the callback is executed by some thread, at some time after the future
   * object is completed. We say that the callback is executed eventually.
   */

  /* Functional Composition and For-Comprehensions */

  // show nesting of callbacks (from the website)

  def getToken(email: String): Future[String] = Future {
    "token"
  }

  // suppose we already know the highest salary
  val highestSalary: Double = 5500000.00

  // this method simulates getting highest salary from the DB (a long running computation)
  def getHighestSalary(token: String): Future[Double] = Future {
    highestSalary
  }

  val email = "employee@email.com"

  // One of the basic combinators is map, which, given a future and a mapping function for the value
  // of the future, produces a new future that is completed with the mapped value once the original
  // future is successfully completed.
  val futureHighestSalary: Future[Double] = getToken(email).map(_ => highestSalary)

  // did you anticipate this return type?
  val futureFutureSalary: Future[Future[Double]] = getToken(email).map(token => getHighestSalary(token))

  // flatMap converts a nested Future[Future[_]] to a Future[_]
  val futureSalary: Future[Double] = getToken(email).flatMap(token => getHighestSalary(token))

  val connection = new CurrencyTradingService

  val USD: String = "USD"
  val CHF: String = "CHF"

  val usdQuote = Future { connection.getCurrentValue(USD) }
  val chfQuote = Future { connection.getCurrentValue(CHF) }

  val purchase: Future[Int] = for {
    usd <- usdQuote
    chf <- chfQuote
    if connection.isProfitable(usd, chf)
  } yield connection.buy(amount, chf)

  purchase foreach { howMany =>
    println("Purchased " + howMany + " CHF")
  }

  // The purchase future is completed only once both usdQuote and chfQuote are completed – it depends on
  // the values of both these futures so its own computation cannot begin earlier. The for-comprehension
  // above is translated into:
  val purchase = usdQuote flatMap {
    usd =>
      chfQuote
        .withFilter(chf => connection.isProfitable(usd, chf))
        .map(chf => connection.buy(amount, chf))
  }
}
