package lectures.part1as

object AdvancedPatternMatching extends App {

  val numbers = List(1)
  val description = numbers match {
    case head :: Nil => println(s"the only element is $head")
    case _ =>
  }

  // pattern matching can be one of the following:
  /*
    - constants
    - wildcards
    - cas classes
    -tuples
    - some special magic like above
   */

  class Person(val name: String, val age: Int)

  object Person {
    def unapply(person: Person): Option[(String, Int)] =
      if (person.age > 21) None
      else Some(person.name, person.age)

    //2.
    def unapply(age: Int): Option[String] =
      Some(if (age > 21) "Im an adult" else "Im a minor")
  }

  val bob = new Person("Bob", 23)
  val greeting = bob match {
    case Person(name, age) => s"Hi my name is $name and I'm $age years old."
    case _ => "No person below the age of 21"
  }
  println(greeting)
  // We can also apply more pattern matching functions to the Person object
  // see 2. in the Person object
  val drinkingStatus = bob.age match {
    case Person(drinkingStatus) => s"My legal status is $drinkingStatus"
  }
  println(drinkingStatus)

  /*
    Exercise.
    Implement pattern matching for the following
   */

  val number: Int = 4
  val mathProperty = number match {
    case x if x < 10 => "single digit"
    case x if x % 2 == 0 => "an even number"
    case _ => "no property"
  }
  println("Before pattern matching ", mathProperty)

  // Exercise implementation
  object even {
    def unapply(arg: Int): Option[Boolean] =
      if (arg % 2 == 0) Some(true)
      else None
  }

  object singleDigit {
    def unapply(arg: Int): Option[Boolean] =
      if (arg > -10 && arg < 10) Some(true)
      else None
  }

  val newNum = 8
  val matchedMathProperty = newNum match {
    case even(_) => "I'm an even number"
    case singleDigit(_) => "I'm a single digit"
    case _ => "No Property"
  }
  println(matchedMathProperty)

  /* Refactoring the above into a fancy way, cleaner code */
  object isNewEven {
    def unapply(arg: Int): Boolean = arg % 2 == 0
  }
  object isNewSingleDigit {
    def unapply(arg: Int): Boolean = arg > -10 && arg < 10
  }

  def num = 14
  val newMatch = num match {
    case isNewEven() => "I am even!"
    case isNewSingleDigit() => "I am a single digit!"
    case _ => "No property :("
  }
  println(newMatch)
}
