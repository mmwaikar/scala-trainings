package com.codionics.day4.actors

/*
 * https://akka.io/try-akka/
 *
 * Akka is a toolkit for building highly concurrent, distributed, and resilient
 * message-driven applications for Java and Scala. Akka is the implementation of
 * the Actor Model on the JVM.
 *
 * Actor model works on message passing - an important difference between passing
 * messages and calling methods is that messages have no return value. By sending
 * a message, an actor delegates work to another actor.
 *
 * Actor model has the concept of supervision - Just like with processes, when an
 * actor fails, its parent actor is notified and it can react to the failure. Also,
 * if the parent actor is stopped, all of its children are recursively stopped, too.
 * This service is called supervision and it is central to Akka.
 *
 * Benefits of using the Actor Model
 *
 * The following characteristics of Akka allow you to solve difficult concurrency
 * and scalability challenges in an intuitive way:
 *
 * Event-driven model — Actors perform work in response to messages. Communication between
 * Actors is asynchronous, allowing Actors to send messages and continue their own work
 * without blocking to wait for a reply.
 *
 * Strong isolation principles — Unlike regular objects in Scala, an Actor does not have a
 * public API in terms of methods that you can invoke. Instead, its public API is defined
 * through messages that the actor handles. This prevents any sharing of state between
 * Actors; the only way to observe another actor’s state is by sending it a message asking for it.
 *
 * Location transparency — The system constructs Actors from a factory and returns references
 * to the instances. Because location doesn’t matter, Actor instances can start, stop, move,
 * and restart to scale up and down as well as recover from unexpected failures.
 *
 * Lightweight — Each instance consumes only a few hundred bytes, which realistically allows
 * millions of concurrent Actors to exist in a single application.
 *
 */
object AkkaEx {

}
