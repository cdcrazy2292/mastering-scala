package lectures.part1as

import scala.annotation.tailrec

object Recap extends App {
  val aCondition: Boolean = false
  val aConditionedVal = if (aCondition) 42 else 65
  // instructions vs expressions
  // In scala we build everything through expressions as opposed to java which is by instructions

  // compiler inders types for us
  val aCodeBlock = {
    if (aCondition) 54
    56
  }

  // Unit = void
  val theUnit = println("hello scala")

  // functions
  def aFunction(x:Int): Int = x + 1

  // recursion: stack and tail
  @tailrec def factorial(n: Int, accumulator: Int): Int =
    if (n <= 0) accumulator
    else factorial(n -1, n * accumulator)

  // object orientation
  class Animal
  class Dog extends Animal
  val aDog: Animal = new Dog // subtyping polymorphism

  trait Carnivore {
    def eat (a: Animal): Unit
  }

  class Crocodile extends Animal with Carnivore {
    override def eat(a: Animal): Unit = println("crunch")
  }

  // method notations
  val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // natural language (infyx notation)

 // 1+2 = 1.+(2), the actual expression that the compiler utilizes

  // anonymous classes
  val aCarnivore = new Carnivore {
    override def eat(a: Animal): Unit = println("roar")
  } // compiler creates a new anonymous class here

  // generics
  abstract class MyList[+A] // variance and variance problems will  be discussed here
  // singleton objects and companions
  object MyList

  // case classes
  case class Person(name: String, age: Int)

  // exceptions and try catch finally
  val throwsException = throw new RuntimeException // type for this is Nothing
  // Nothing can't be instantiated. Nothingness
  val aPotentialFailure = try {
    throw new RuntimeException
  } catch {
    case e: Exception => "I caught an Exception"
  } finally {
    println("some logs")
  }

  // packaging and imports (we wont care too much here)

  // everything is scala is an object
  // functional programming
  // functions are instances of classes with apply methods
  val increment = new Function1[Int, Int] {
    override def apply(v1: Int): Int = v1 + 1
  }
  increment(1)

  //First class support
  val anonymousIncrementor = (x: Int) => x + 1 // First class support for lambdas
  List(1,2,3).map(anonymousIncrementor) // Higher Order Function
  // map, flatmap, filter
  // for comprehension

  val pairs = for {
    num <- List(1,2,3)
    char <- List('1', 'b', 'c')
  } yield num + "-" + char // cross pairing between each element in nums and chars

  // Scala collections, sequences, arrays, lists, vectors, maps, tuples
  val aMap = Map(
    "Daniel" -> 789,
    "Jesse" -> 555
  )

  // "collections" which are more abstract computations. Options, Try
  val anOption = Some(2)

  // pattern matching (switch on steroids)
  val x = 2
  val order = x match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "thirdf"
    case _ =>  x + "th"
  }

  val bob = Person("Bob", 22)
  val greeting = bob match {
    case Person(n, _) => s"Hi, my nam is $n"
  }
  // match throws exceptions when we exhaust all the patterns
}
