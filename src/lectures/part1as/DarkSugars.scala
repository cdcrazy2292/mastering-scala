package lectures.part1as

import java.awt.Composite

import scala.util.Try

object DarkSugars extends App {
  // syntax sugar #1: Methods with single params
  def singleArgMethod(arg: Int): String = s"$arg little ducks..."
  // It can be written as follows
  val description = singleArgMethod {
    // Some crazy code here but we need to return its type
    42
  }
  println(42)
  // A real case use of this sugar is the Try method
  val aTryInstance = Try {
    throw new RuntimeException
  }

  List(1,2,3).map {x =>
    x + 1
  }


  // Syntax sugar #2: Single abstract method pattern
  trait Action {
    def act(x: Int): Int
  }

  // This is an anonymous function
  val anInstance: Action = new Action {
    override def act(x: Int): Int = x + 1
  }// this is converted to the below

  val aFunkyInstance: Action = (x: Int) => x + 1 // This is the same as the above expression

  // real world example Runnables
  val aThread = new Thread(new Runnable {
    override def run(): Unit = println("Hello, Scala")
  })// The long way. The below is the lambda expressions

  val aSweeterThread = new Thread(() => println("sweet scala"))

  // It also works for classes that have only one method NOT IMPLEMENTED. Example below

  abstract class AnAbstractType {
    def implemented: Int = 23
    def f(a: Int): Unit
  } // class with one unimplemented method

  val abstractInstance: AnAbstractType = (a: Int) => println("sweet")

  // Syntax Sugar #3: The :: and #:: methods are special

  val prependedList = 2 :: List(3,4)// How does this work????
  // after infix understanding it would be 2.::(List(3,4) but NOT QUITE
  // The compiler actually writes it like this -> List(3,4).::(2)
  // How on Earth??????
  // Scala specs: last character decides the associativity of the method.
  // If it ends in a :, it is Right associative, if not, it is left which is the common behavior of the language
  1 :: 2 :: 3 :: 4 :: List(5, 6)
  // is equivalent to
  List(5, 6).::(3).::(2).::(1).::(0)

  // real world example. Streams
  class MyStream[T] {
    def -->:(value: T): MyStream[T] = this // actual implementation here
  }
  val myStream = 1 -->: 2 -->: 3 -->: new MyStream[Int]
  // Therefore the operators are not really special, they are different because they are right associative ( In reverse )

  // Syntax sugar #4: multi-word method naming
  class TeenGirl(name: String) {
    def `and then said`(message: String): Unit = println(s"$name said $message")
  }

  var lilly = new TeenGirl("Lilly")
  lilly `and then said`("Scala is kinda weird!!")

  // Syntax sugar #5: Generic Infix notation
  class Composite[A, B]
  val composite: Composite[Int, String] = ??? // actual way that the compiler understands
  val infixedComposite: Int Composite String = ???
  // another example
  class -->[A,B]
  val towards: Int --> String = ???

  // Syntax sugar #6: update() is a special method like apply()
  val anArray = Array(1,2,3)
  anArray(2) = 7 // is converted by the compiler into the below
  anArray.update(2, 7)
  // update is used in mutable collections
  // Now we need to remember apply() and update()

  // Syntax Sugar #7: setter method in a Mutable class
  class Mutable {
    private var privateNumber: Int = 0 // private for OO encapsulation
    def number: Int = privateNumber
    def number_=(value: Int): Unit = privateNumber = value // setter
  }

  // using the setter method
  val aMutableContainer = new Mutable
  aMutableContainer.number = 50 // This is actually using the setter method we created in the class




}
